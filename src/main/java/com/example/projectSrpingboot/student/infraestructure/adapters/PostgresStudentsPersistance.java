package com.example.projectSrpingboot.student.infraestructure.adapters;

import com.example.projectSrpingboot.exception.StudentNotFoundException;
import com.example.projectSrpingboot.student.domain.interfaces.StudentPersistence;
import com.example.projectSrpingboot.student.infraestructure.StudentRepository;
import com.example.projectSrpingboot.student.infraestructure.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Qualifier("PostgresAccountPersistence")
@AllArgsConstructor
@Slf4j
public class PostgresStudentsPersistance implements StudentPersistence {

    public final StudentRepository studentRepository;

    public List<StudentEntity> getStudents() {
        return studentRepository.findAll();
    }

    public StudentEntity addNewStudent(StudentEntity student) {
        log.info("Create students service called");
        Optional<StudentEntity> studentOptional = studentRepository.findStudentsByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new StudentNotFoundException("student with ID "+ studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public StudentEntity updateStudent(Long studentId, String name, String email){
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(
                        ()-> new StudentNotFoundException("student with ID "+ studentId + " does not exists")
                );
        if(name != null
                && name.length()>0
                && !Objects.equals(student.getName(),name)){
                student.setName(name);
        }
        if(email != null
                && email.length()>0
                && !Objects.equals(student.getEmail(),email)){
            Optional<StudentEntity> studentOptional = studentRepository.findStudentsByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
        return studentRepository.save(student);
    }
}

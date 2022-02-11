package com.example.projectSrpingboot.student;

import com.example.projectSrpingboot.exception.StudentNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class StudentService {

    public final StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addNewStudent(Student student) {
        log.info("Create students service called");
        Optional<Student> studentOptional = studentRepository.findStudentsByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("student with ID "+ studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public Student updateStudent(Long studentId, String name, String email){
        Student student = studentRepository.findById(studentId)
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
            Optional<Student> studentOptional = studentRepository.findStudentsByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
        return studentRepository.save(student);
    }
}

package com.example.projectSrpingboot.student.application.impl;

import com.example.projectSrpingboot.exception.GeneralResponse;
import com.example.projectSrpingboot.student.application.IStudent;
import com.example.projectSrpingboot.student.infraestructure.adapters.PostgresStudentsPersistance;
import com.example.projectSrpingboot.student.infraestructure.controllers.dto.StudentEntityDto;
import com.example.projectSrpingboot.student.infraestructure.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentImpl implements IStudent {

    @Autowired
    public PostgresStudentsPersistance postgresStudentsPersistance;

    @Override
    public ResponseEntity<StudentEntity> createStudent(StudentEntityDto student) {
        StudentEntity newStudent = postgresStudentsPersistance.addNewStudent(student.toEntity());
        return new ResponseEntity<>(newStudent,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<StudentEntity>> getStudents() {
        List<StudentEntity> students = postgresStudentsPersistance.getStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GeneralResponse<String>> deleteStudents(Long id) {
        postgresStudentsPersistance.deleteStudent(id);
        return new GeneralResponse<>(true,"success","Student with id "+id+" was delete successfully").createResponse();
    }

    @Override
    public ResponseEntity<StudentEntity> updateStudent(Long id, StudentEntityDto studentEntityDto) {
        StudentEntity updateStudent = postgresStudentsPersistance.updateStudent(id,studentEntityDto.getName(), studentEntityDto.getEmail());
        return new ResponseEntity<>(updateStudent,HttpStatus.OK);
    }
}

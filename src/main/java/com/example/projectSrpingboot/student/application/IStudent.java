package com.example.projectSrpingboot.student.application;

import com.example.projectSrpingboot.exception.GeneralResponse;
import com.example.projectSrpingboot.student.domain.Student;
import com.example.projectSrpingboot.student.infraestructure.controllers.dto.StudentEntityDto;
import com.example.projectSrpingboot.student.infraestructure.entity.StudentEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IStudent {
    ResponseEntity<StudentEntity> createStudent(StudentEntityDto student);
    ResponseEntity<List<StudentEntity>> getStudents();
    ResponseEntity<GeneralResponse<String>> deleteStudents(Long id);
    ResponseEntity<StudentEntity> updateStudent(Long id, StudentEntityDto studentEntityDto);
}

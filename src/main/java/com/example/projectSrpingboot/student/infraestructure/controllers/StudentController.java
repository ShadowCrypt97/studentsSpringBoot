package com.example.projectSrpingboot.student.infraestructure.controllers;

import com.example.projectSrpingboot.exception.GeneralResponse;
import com.example.projectSrpingboot.student.application.IStudent;
import com.example.projectSrpingboot.student.infraestructure.adapters.PostgresStudentsPersistance;
import com.example.projectSrpingboot.student.infraestructure.controllers.dto.StudentEntityDto;
import com.example.projectSrpingboot.student.infraestructure.entity.StudentEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/student")
public class StudentController {

    @Autowired
    IStudent iStudent;

    private final PostgresStudentsPersistance postgresStudentsPersistance;

    @GetMapping
    public ResponseEntity<List<StudentEntity>> getStudents(){
        return iStudent.getStudents();
    }

    @PostMapping
    public ResponseEntity<StudentEntity> registerNewStudent(@RequestBody @Valid StudentEntityDto student){
        return iStudent.createStudent(student);
    }
    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<GeneralResponse<String>> deleteStudent(@PathVariable ("studentId") Long id){
        return iStudent.deleteStudents(id);
    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable ("studentId") Long id,
                                                       @RequestBody @Valid StudentEntityDto student){
        return iStudent.updateStudent(id, student);
    }
}

package com.example.projectSrpingboot.student.infraestructure.controllers;

import com.example.projectSrpingboot.student.infraestructure.adapters.PostgresStudentsPersistance;
import com.example.projectSrpingboot.student.infraestructure.entity.StudentEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final PostgresStudentsPersistance studentService;

    @GetMapping
    public ResponseEntity<List<StudentEntity>> getStudents(){
        List<StudentEntity> students = studentService.getStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentEntity> registerNewStudent(@RequestBody @Valid StudentEntity student){
        StudentEntity newStudent = studentService.addNewStudent(student);
        return new ResponseEntity<>(newStudent,HttpStatus.CREATED);
    }
    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable ("studentId") Long id){
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable ("studentId") Long id,
                                                       @RequestParam(required = false) String name,
                                                       @RequestParam(required = false) String email){
        StudentEntity updateStudent = studentService.updateStudent(id,name, email);
        return new ResponseEntity<>(updateStudent,HttpStatus.OK);

    }
}

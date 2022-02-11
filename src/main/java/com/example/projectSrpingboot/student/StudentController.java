package com.example.projectSrpingboot.student;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = studentService.getStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> registerNewStudent(@RequestBody Student student){
        Student newStudent = studentService.addNewStudent(student);
        return new ResponseEntity<>(newStudent,HttpStatus.CREATED);
    }
    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable ("studentId") Long id){
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable ("studentId") Long id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){
        Student updateStudent = studentService.updateStudent(id,name, email);
        return new ResponseEntity<>(updateStudent,HttpStatus.OK);

    }
}

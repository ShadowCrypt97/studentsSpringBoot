package com.example.projectSrpingboot.student.domain.interfaces;

import com.example.projectSrpingboot.student.infraestructure.entity.StudentEntity;

import java.util.List;

public interface StudentPersistence {

    List<StudentEntity> getStudents();

    StudentEntity addNewStudent(StudentEntity student);

    void deleteStudent(Long studentId);

    StudentEntity updateStudent(Long studentId, String name, String email);


}

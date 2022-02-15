package com.example.projectSrpingboot.student.infraestructure;

import com.example.projectSrpingboot.student.infraestructure.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
    //SELECT * FROM students WHERE email = ?
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<StudentEntity> findStudentsByEmail(String email);
}

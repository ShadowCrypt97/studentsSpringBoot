package com.example.projectSrpingboot.student.infraestructure.entity;

import com.example.projectSrpingboot.student.domain.Student;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentEntity implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    private String name;
    @Transient
    private Integer age;
    private LocalDate dateOfBirth;
    private String email;

    public Integer getAge() {
        return Period.between(this.dateOfBirth,LocalDate.now()).getYears();
    }

    public StudentEntity(String name,
                         LocalDate dateOfBirth,
                         String email) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public StudentEntity toEntity(Student student) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(student.getId());
        studentEntity.setEmail(student.getEmail());
        studentEntity.setName(student.getName());
        studentEntity.setAge(student.getAge());
        studentEntity.setDateOfBirth(student.getDateOfBirth());
        return studentEntity;
    }
}

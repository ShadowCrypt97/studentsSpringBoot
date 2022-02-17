package com.example.projectSrpingboot.student.infraestructure.controllers.dto;

import com.example.projectSrpingboot.student.domain.Student;
import com.example.projectSrpingboot.student.infraestructure.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntityDto implements Serializable {
    private Long id;
    @NotEmpty(message = "{name.notempty}")
    @Size(min = 3, message = "{name.size}")
    private String name;
    private Integer age;
    private LocalDate dateOfBirth;
    @NotEmpty(message = "{email.notempty}")
    @Email(message = "{email.regex}")
    private String email;

    public Student toStudentAccount(){
        return Student.builder()
                .id(this.id)
                .email(this.email)
                .age(this.age)
                .name(this.name)
                .dateOfBirth(this.dateOfBirth)
                .build();
    }
    public StudentEntity toEntity() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(this.id);
        studentEntity.setEmail(this.email);
        studentEntity.setName(this.name);
        studentEntity.setAge(this.age);
        studentEntity.setDateOfBirth(this.dateOfBirth);
        return studentEntity;
    }
 }

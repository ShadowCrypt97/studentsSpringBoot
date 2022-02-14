package com.example.projectSrpingboot.student;

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
@EqualsAndHashCode
@ToString
@Data
public class Student implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    @NotEmpty(message = "{name.notempty}")
    @Size(min = 3,message = "{name.size}")
    private String name;
    @Transient
    private Integer age;
    private LocalDate dateOfBirth;
    @NotEmpty(message = "{email.notempty}")
    @Email(message = "{email.regex}")
    private String email;

    public Student(String name,
                   LocalDate dateOfBirth,
                   String email) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public Integer getAge() {
        return Period.between(this.dateOfBirth,LocalDate.now()).getYears();
    }
}

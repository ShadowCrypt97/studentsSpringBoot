package com.example.projectSrpingboot.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student sergio = new Student(
                "Sergio",
                LocalDate.of(1997, FEBRUARY, 28),
                "sergio@gmail.com"
            );
            Student maria = new Student(
                    "Maria",
                    LocalDate.of(2001, DECEMBER, 12),
                    "maria@gmail.com"
            );

            studentRepository.saveAll(
                    List.of(sergio,maria)
            );
        };
    }
}

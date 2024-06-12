package com.example.firstapp.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;
import static java.time.Month.MAY;


@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
           Student manasseh = new Student(
                    "Manasseh",
                    "manasseh@gmail.com",
                    LocalDate.of(2005,MAY,17)
            );
            Student tom = new Student(
                    "Tom",
                    "tom@gmail.com",
                    LocalDate.of(1990, JANUARY,1)

                    );

            repository.saveAll(
                    List.of(manasseh,tom)
            );
        };
    }
}

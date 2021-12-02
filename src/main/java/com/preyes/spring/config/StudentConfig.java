package com.preyes.spring.config;

import com.preyes.spring.entity.Student;
import com.preyes.spring.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student pablo = new Student("Pablo",
                    "pablo@gmail.com",
                    LocalDate.of(1989, Month.JULY, 28)
            );
            Student paola = new Student(
                    "Paola",
                    "paola@gmail.com",
                    LocalDate.of(1989, Month.SEPTEMBER, 14)
            );
            Student virginia = new Student(
                    "Virginia",
                    "virginia@gmail.com",
                    LocalDate.of(1958, Month.JUNE, 14)
            );
            repository.save(pablo);
            repository.save(paola);
            repository.save(virginia);
        };
    }
}

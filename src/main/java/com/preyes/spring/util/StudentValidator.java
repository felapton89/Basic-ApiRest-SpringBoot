package com.preyes.spring.util;

import com.preyes.spring.entity.Student;
import com.preyes.spring.exception.StudentValidatorException;

public class StudentValidator {

    private StudentValidator() {
    }

    public static void validator(Student student){
        if (student.getName() == null || student.getName().isEmpty()){
            throw new StudentValidatorException("Name not be empty.");
        }
        if (student.getEmail() == null || student.getEmail().isEmpty()){
            throw new StudentValidatorException("Email not be empty.");
        }
    }
}

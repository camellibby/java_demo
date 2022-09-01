package com.camellibby.foo.spring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    @Qualifier
    public Student getStudent1() {
        Student student = new Student();
        student.setName("student1");
        return student;
    }

    @Bean
    @Qualifier
    public Student getStudent2() {
        Student student = new Student();
        student.setName("student2");
        return student;
    }
}

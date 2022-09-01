package com.camellibby.foo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Service {
    @Autowired
    private Person person;

    @Autowired
    @Qualifier
    private List<Student> students;

    public Person getPerson() {
        return person;
    }

    public List<Student> getStudents() {
        return students;
    }
}

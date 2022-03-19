package com.camellibby.springboot.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author luoxinliang
 */
public class Config {
    private String status;
    private Person person;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
            property = "type"
    )
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Student.class, name = "Student"),
            @JsonSubTypes.Type(value = Teacher.class, name = "Teacher")
    })
    public void setPerson(Person person) {
        this.person = person;
    }
}

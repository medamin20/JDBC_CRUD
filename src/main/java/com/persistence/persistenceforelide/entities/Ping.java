package com.persistence.persistenceforelide.entities;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Slf4j
//@Getter
//@Setter
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Ping implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Ping.class);


    private int id;

    private String name ;

    private String email ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Ping() {
    }

    public Ping(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }


    public Ping(String name, String email) {
        this.name = name;
        this.email = email;
    }


    @Override
    public String toString() {
        return "Ping{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "user", schema = "public")
public class User {


    @Id @GeneratedValue
    @Column(name = "userId")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "course")
    private Integer course;

    @Column(name = "user_group")
    private String user_group;

//    @JsonManagedReference(value="user-movement")
//    @OneToMany(mappedBy="userId", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//    private List<TestResult> testResult;

    public User() {
    }

    public User(String name, String surname, String patronymic, Integer course, String group) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.course = course;
        this.user_group = group;
    }

}
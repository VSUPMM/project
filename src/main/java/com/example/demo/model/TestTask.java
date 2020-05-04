package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.Type;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@Entity
@Table(name = "test_task")
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class)
public class TestTask {


    @Id
    @GeneratedValue
    @Column(name = "taskId")
    private  Long  taskId;

    @Column(name = "question")
    private String question;

    @Column(name = "value")
    private Integer value;

    @Type(type = "list-array")
    @Column(name = "possible_answers", columnDefinition = "text[]")
    private List<String> possibleAnswers;

    @Type(type = "list-array")
    @Column(name = "right_answers", columnDefinition = "text[]")
    private List<String> rightAnswers;

    @JsonBackReference(value="TestTask-movement")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name ="test_id")
    private Test testTaskTest;

    public TestTask() {
    }

    public TestTask(String question, Integer value, Test ftest) {
        this.question = question;
        this.value = value;
        this.testTaskTest = ftest;
    }

}


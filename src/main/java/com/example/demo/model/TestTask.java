package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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

    @JsonManagedReference(value="TestAnswer-movement")
    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy="task", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<TestAnswer> answers ;

    public TestTask() {
    }

    public TestTask(String question, Integer value, List<String> possibleAnswers,
                    List<String> rightAnswers, Test testTaskTest) {
        this.question = question;
        this.value = value;
        this.possibleAnswers = possibleAnswers;
        this.rightAnswers = rightAnswers;
        this.testTaskTest = testTaskTest;
    }
}


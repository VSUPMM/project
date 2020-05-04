package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.Objects;

@Data
@Entity
@Table(name = "practical_task")
public class PracticalTask {


    @Id @GeneratedValue
    @Column(name = "task_id")
    private  Long  taskId;

    @Column(name = "question")
    private String question;

    @Column(name = "value")
    private Integer value;

    @Column(name = "rightQuery")
    String rightQuery;

    @JsonBackReference(value="PracticalTask-movement")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name ="test_id")
    private Test practTaskTest;

    public PracticalTask(String question, Integer value, Test test) {

        this.question = question;
        this.value = value;
        this.practTaskTest = test;
    }

    public PracticalTask() {
    }

}

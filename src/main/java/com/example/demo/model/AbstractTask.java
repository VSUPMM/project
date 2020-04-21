package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "task")
public class AbstractTask {
    //@Column(name = "taskId")
    @Id @GeneratedValue
    private  Integer  TaskId;

   @Column(name = "question")
    private String question;

   @Column(name = "value")
    private Integer value;

    public AbstractTask(String question, Integer value) {

        this.question = question;
        this.value = value;
    }

    public AbstractTask() {
    }
}

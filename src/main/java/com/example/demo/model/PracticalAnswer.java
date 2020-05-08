package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "practical_answer")
public class PracticalAnswer {

    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private Long answerId;

    @Column(name = "user_query")
    private String userQuery;

    @Column(name = "correct")
    private Boolean correct;

    @JsonBackReference(value="PracticalAnswer-movement")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name ="task_id")
    private PracticalTask task;

    public PracticalAnswer(String userQuery, Boolean correct, PracticalTask task) {
        this.userQuery = userQuery;
        this.correct = correct;
        this.task = task;
    }

    public PracticalAnswer() {
    }
}

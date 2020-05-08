package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "test_answer")
public class TestAnswer {

    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private Long answerId;

    @Type(type = "list-array")
    @Column(name = "answer", columnDefinition = "text[]")
    private List<String> answer;

    @Column(name = "correct")
    private Boolean correct;

    @JsonBackReference(value="TestAnswer-movement")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name ="task_id")
    private TestTask task;

    public TestAnswer(List<String> answer, Boolean correct, TestTask task) {
        this.answer = answer;
        this.correct = correct;
        this.task = task;
    }

    public TestAnswer() {
    }
}

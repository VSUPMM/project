package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.List;
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
    private String rightQuery;

    @JsonBackReference(value="PracticalTask-movement")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name ="test_id")
    private Test practTaskTest;

    @JsonManagedReference(value="PracticalAnswer-movement")
    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy="task", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<PracticalAnswer> answers ;

    public PracticalTask(String question, String rightQuery, Integer value, Test practTaskTest) {
        this.question = question;
        this.value = value;
        this.rightQuery = rightQuery;
        this.practTaskTest = practTaskTest;
    }

    public PracticalTask() {
    }

}

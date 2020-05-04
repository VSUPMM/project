package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;


@Data
@Entity
@Table(name = "test_result")
public class TestResult {


    @EmbeddedId
    private TestUserId id;

    @JsonBackReference(value="testResult-movement")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @MapsId("fk_test")
    @JoinColumn(name = "fk_test")
    private Test testId;


    @JsonBackReference(value="user-movement")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @MapsId("fk_user")
    @JoinColumn(name = "fk_user")
    private User userId;

    public TestResult(Test test, User user) {
        id = new TestUserId(test.getTestId(),user.getUserId());
        this.testId = test;
        this.userId = user;
    }

    public TestResult() {

    }

    public String getId() {
        String result = "user " + id.getuserId() + " test " + id.gettestId();
        return result;
    }

    public void setId(TestUserId id) {
        this.id = id;
    }

}


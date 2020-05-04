package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TestUserId implements Serializable {

    @Column(name = "fk_user")
    private Long userId;

    @Column(name = "fk_test")
    private Long testId;

    private TestUserId() {}

    public TestUserId(
            Long testId, Long userId
            ) {
        this.userId = userId;
        this.testId = testId;
    }

    public Long getuserId() {
        return userId;
    }

    public Long gettestId() {
        return testId;
    }

}
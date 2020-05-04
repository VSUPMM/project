package com.example.demo.repository;

import com.example.demo.model.TestResult;
import com.example.demo.model.TestUserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultRepository extends JpaRepository<TestResult, TestUserId> {
}

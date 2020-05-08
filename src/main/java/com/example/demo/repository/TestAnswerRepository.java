package com.example.demo.repository;

import com.example.demo.model.PracticalTask;
import com.example.demo.model.TestAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestAnswerRepository extends JpaRepository<TestAnswer, Long> {

}
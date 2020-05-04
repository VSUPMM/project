package com.example.demo.repository;


import com.example.demo.model.TestTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestTaskRepository extends JpaRepository<TestTask, Long> {
}

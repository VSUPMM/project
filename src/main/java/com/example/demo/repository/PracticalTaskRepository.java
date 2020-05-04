package com.example.demo.repository;


import com.example.demo.model.PracticalTask;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PracticalTaskRepository extends JpaRepository<PracticalTask, Long> {

}
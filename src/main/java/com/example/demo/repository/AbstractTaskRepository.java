package com.example.demo.repository;


import com.example.demo.model.AbstractTask;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AbstractTaskRepository extends JpaRepository<AbstractTask, Integer> {


}
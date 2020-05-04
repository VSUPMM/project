package com.example.demo.controller;

import com.example.demo.model.TestResult;

import com.example.demo.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResultController {

    private ResultService service;

    @Autowired
    public void setService(ResultService service) {
        this.service = service;
    }

    @PostMapping("/result/user/{userId}/test/{testId}")
    public ResponseEntity<?> newTestResult(@PathVariable Long userId,@PathVariable Long testId) {
        return ResponseEntity.ok(service.createResult(userId,testId));

    }

    @GetMapping("/result/user/{userId}/test/{testId}")
    public ResponseEntity<TestResult> one(@PathVariable Long userId, @PathVariable Long testId) {
        return ResponseEntity.ok(service.getById(userId,testId));
    }


    @GetMapping("/results")
    public ResponseEntity<List<TestResult>> all() {
        return ResponseEntity.ok(service.getAll());
    }

//    @PutMapping("/results/user/{userId}/test/{testId}")
//    ResponseEntity<?> replaceTest(@RequestBody TestResult newResult, @PathVariable Long userId,@PathVariable Long testId) {
//        return ResponseEntity.ok(service.updateResult(newResult,userId,testId));
//    }

    @DeleteMapping("/result/user/{userId}/test/{testId}")
    public ResponseEntity<Object> deleteTest(@PathVariable Long userId,@PathVariable Long testId) {
        service.deleteResult(userId,testId);
        return ResponseEntity.noContent().build();
    }
}
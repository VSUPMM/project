package com.example.demo.controller;



import com.example.demo.model.PracticalAnswer;
import com.example.demo.model.PracticalTask;
import com.example.demo.model.Test;
import com.example.demo.service.PracticalAnswerService;
import com.example.demo.service.PracticalTaskService;
import com.example.demo.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;


import java.net.URISyntaxException;
import java.util.List;


@RestController
public class TestController {


    private TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    private PracticalAnswerService answerService;

    @Autowired
    public void setPracticalAnswerService(PracticalAnswerService answerService) {
        this.answerService = answerService;
    }

    private PracticalTaskService taskService;

    @Autowired
    public void setService(PracticalTaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/test")
    ResponseEntity<Test> newTest(@RequestBody Test newTest) {
        return ResponseEntity.ok(testService.createTest(newTest));
    }

    @PostMapping("/panswer")
    ResponseEntity<PracticalAnswer> newTest(@RequestBody PracticalAnswer practicalAnswer) {
        return ResponseEntity.ok(answerService.createAnswer(practicalAnswer));
    }

    @PostMapping("/ptask")
    ResponseEntity<PracticalTask> newTest(@RequestBody PracticalTask practicalTask) {
        return ResponseEntity.ok(taskService.createTask(practicalTask));
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<Test> one(@PathVariable Long id) {
        return ResponseEntity.ok(testService.getById(id));
    }


    @GetMapping("/tests")
    public ResponseEntity<List<Test>> all() {
       return ResponseEntity.ok(testService.getAll());
    }

    @PutMapping("/tests/{id}")
    public ResponseEntity<Test> replaceTest(@RequestBody Test newTest, @PathVariable Long id) throws URISyntaxException {
       return ResponseEntity.ok(testService.updateTest(newTest,id));
    }

    @DeleteMapping("/test/{id}")
    ResponseEntity<Object> deleteTest(@PathVariable Long id) {
        testService.deleteTest(id);
        return ResponseEntity.noContent().build();
    }
}

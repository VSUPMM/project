package com.example.demo.controller;



import com.example.demo.model.Test;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;


import java.net.URISyntaxException;
import java.util.List;


@RestController
public class TestController {


    private TestService service;

    @Autowired
    public void setService(TestService service) {
        this.service = service;
    }

    @PostMapping("/test")
    ResponseEntity<Test> newTest(@RequestBody Test newTest) {
        return ResponseEntity.ok(service.createTest(newTest));
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<Test> one(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }


    @GetMapping("/tests")
    public ResponseEntity<List<Test>> all() {
       return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/tests/{id}")
    public ResponseEntity<Test> replaceTest(@RequestBody Test newTest, @PathVariable Long id) throws URISyntaxException {
       return ResponseEntity.ok(service.updateTest(newTest,id));
    }

    @DeleteMapping("/test/{id}")
    ResponseEntity<Object> deleteTest(@PathVariable Long id) {
        service.deleteTest(id);
        return ResponseEntity.noContent().build();
    }
}

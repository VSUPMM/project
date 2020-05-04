package com.example.demo.service;

import com.example.demo.exception.ResultNotFoundException;

import com.example.demo.model.Test;
import com.example.demo.model.TestResult;
import com.example.demo.model.TestUserId;

import com.example.demo.model.User;
import com.example.demo.repository.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    TestResultRepository resultRepository;

    private TestService testService;

    private UserService userService;

    @Autowired
    public void setService(UserService service) {
        this.userService = service;
    }

    @Autowired
    public void setService(TestService service) {
        this.testService = service;
    }

    @Autowired
    public void setRepository(TestResultRepository repository) {
        this.resultRepository = repository;
    }


    @Transactional
    public void deleteResult(Long userId, Long testId) {
        resultRepository.deleteById(new TestUserId(testId,userId));
    }

    @Transactional
    public List<TestResult> getAll() {
        List<TestResult> resultEntities = resultRepository.findAll();
        return resultEntities;
    }

    @Transactional
    public TestResult getById(Long userId, Long testId) {
        Optional<TestResult> result = resultRepository.findById(new TestUserId(testId,userId));
        if (result.isPresent()){
            return result.get();
        }
        else
            throw new ResultNotFoundException(userId, testId);

    }

    @Transactional
    public TestResult createResult(Long userId, Long testId) {
        Test test = testService.getById(testId);
        User user = userService.getById(userId);
        TestResult savedResult = resultRepository.save(new TestResult(test,user));
        return savedResult;
    }
//
//    @Transactional
//    public TestResult updateResult(TestResult newResult, Long userId, Long testId) {
//
//        TestResult updatedResult = resultRepository.findById(new TestUserId(testId,userId))
//                .map(result -> {
//                    result.setFk_test(newResult.getFk_test());
//                    result.setFk_user(newResult.getFk_user());
//                    return resultRepository.save(result);
//                })
//                .orElseGet(() -> {
//                    newResult.setId(new TestUserId(testId,userId));
//                    return resultRepository.save(newResult);
//                });
//
//
//        return updatedResult;
//    }
    
}

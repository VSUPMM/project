package com.example.demo.service;


import com.example.demo.exception.TestNotFoundException;
import com.example.demo.model.PracticalTask;
import com.example.demo.model.Test;
import com.example.demo.model.TestTask;
import com.example.demo.repository.PracticalTaskRepository;
import com.example.demo.repository.TestRepository;
import com.example.demo.repository.TestTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    private TestRepository testRepository;

    private PracticalTaskService practTaskService;

    private TestTaskService testTaskService;

    @Autowired
    public void setTestRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Autowired
    public void setPractTaskService(PracticalTaskService practTaskService) {
        this.practTaskService = practTaskService;
    }

    @Autowired
    public void setTestTaskService(TestTaskService testTaskService) {
        this.testTaskService = testTaskService;
    }

    @Transactional
    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }

    @Transactional
    public  List<Test> getAll() {
        List<Test> testEntities = testRepository.findAll();
        return testEntities;
    }

    @Transactional
    public Test getById(Long id) {
        Optional<Test> test = testRepository.findById(id);
        if (test.isPresent()){
            return test.get();
        }
        else
            throw new TestNotFoundException(id);

    }

    @Transactional
    public Test createTest(Test newTest) {

        newTest = testRepository.save(newTest);

        List<PracticalTask> ptasks = newTest.getPracticalTasks();

        for(PracticalTask task:ptasks){
            task.setPractTaskTest(newTest);   // TO DO!!!!!!!!!!!!!!
            practTaskService.createTask(task);
        }

        List<TestTask> ttasks = newTest.getTestTasks();

        for(TestTask task:ttasks){
            task.setTestTaskTest(newTest);      // TO DO!!!!!!!!!!!!!!
            testTaskService.createTask(task);
        }


        return newTest;
    }

    @Transactional
    public Test updateTest(Test newTest, Long id) {
        Test updatedTest = testRepository.findById(id)
                .map(test -> {
                    test.setName(newTest.getName());
                    return testRepository.save(test);
                })
                .orElseGet(() -> {
                    newTest.setTestId(id);
                    return testRepository.save(newTest);
                });


        return updatedTest;
    }
}

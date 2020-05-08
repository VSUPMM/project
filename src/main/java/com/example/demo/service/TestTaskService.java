package com.example.demo.service;

import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.TestAnswer;
import com.example.demo.model.TestTask;

import com.example.demo.repository.TestTaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TestTaskService {

    private TestTaskRepository repository;

    @Autowired
    public void setRepository(TestTaskRepository repository) {
        this.repository = repository;
    }
    
    private TestAnswerService answerSevice;

    @Autowired
    public void setRepository(TestAnswerService answerSevice) {
        this.answerSevice = answerSevice;
    }
    
    @Transactional
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public List<TestTask> getAll() {
        List<TestTask> taskEntities = repository.findAll();
        return taskEntities;
    }

    @Transactional
    public TestTask getById(Long id) {
        Optional<TestTask> task = repository.findById(id);
        if (task.isPresent()){
            return task.get();
        }
        else
            throw new TaskNotFoundException(id);

    }

    @Transactional
    public TestTask createTask(TestTask newTask) {
        
        newTask = repository.save(newTask);

//        List<TestAnswer> answers = newTask.getAnswers();
//
//        for(TestAnswer answer :answers){
//            answer.setTask(newTask);   // TO DO!!!!!!!!!!!!!!
//            answerSevice.createAnswer(answer);
//        }

        return newTask;
    }

    @Transactional
    public TestTask updateTask(TestTask newTestTask, Long id) {

        TestTask updatedTestTask = repository.findById(id)
                .map(task -> {
                    task.setQuestion(newTestTask.getQuestion());
                    task.setValue(newTestTask.getValue());
                    task.setRightAnswers(newTestTask.getRightAnswers());
                    return repository.save(task);
                })
                .orElseGet(() -> {
                    newTestTask.setTaskId(id);
                    return repository.save(newTestTask);
                });

        return updatedTestTask;
    }

}

package com.example.demo.service;

import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.PracticalTask;

import com.example.demo.repository.PracticalTaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PracticalTaskService {

    @Autowired
    private PracticalTaskRepository repository;
    

    @Autowired
    public void setRepository(PracticalTaskRepository repository) {
        this.repository = repository;
    }


    @Transactional
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public List<PracticalTask> getAll() {
        List<PracticalTask> taskEntities = repository.findAll();
        return taskEntities;
    }

    @Transactional
    public PracticalTask getById(Long id) {
        Optional<PracticalTask> task = repository.findById(id);
        if (task.isPresent()){
            return task.get();
        }
        else
            throw new TaskNotFoundException(id);

    }

    @Transactional
    public PracticalTask createTask(PracticalTask newTask) {
        newTask = repository.save(newTask);

        return newTask;
    }

    @Transactional
    public PracticalTask updateTask(PracticalTask newPracticalTask, Long id) {

        PracticalTask updatedPracticalTask = repository.findById(id)
                .map(task -> {
                    task.setQuestion(newPracticalTask.getQuestion());
                    task.setValue(newPracticalTask.getValue());
                    task.setRightQuery(newPracticalTask.getRightQuery());
                    return repository.save(task);
                })
                .orElseGet(() -> {
                    newPracticalTask.setTaskId(id);
                    return repository.save(newPracticalTask);
                });



        return updatedPracticalTask;
    }
    
}

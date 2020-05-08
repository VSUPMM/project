package com.example.demo.service;

import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.PracticalAnswer;
import com.example.demo.model.PracticalTask;

import com.example.demo.repository.PracticalAnswerRepository;
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
    private PracticalTaskRepository taskRepository;

    @Autowired
    public void setRepository(PracticalTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Autowired
    private PracticalAnswerService answerSevice;

    @Autowired
    public void setRepository(PracticalAnswerService answerSevice) {
        this.answerSevice = answerSevice;
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public List<PracticalTask> getAll() {
        List<PracticalTask> taskEntities = taskRepository.findAll();
        return taskEntities;
    }

    @Transactional
    public PracticalTask getById(Long id) {
        Optional<PracticalTask> task = taskRepository.findById(id);
        if (task.isPresent()){
            return task.get();
        }
        else
            throw new TaskNotFoundException(id);

    }

    @Transactional
    public PracticalTask createTask(PracticalTask newTask) {
        newTask = taskRepository.save(newTask);

        List<PracticalAnswer> answers = newTask.getAnswers();

        for(PracticalAnswer answer :answers){
            answer.setTask(newTask);   // TO DO!!!!!!!!!!!!!!
            answerSevice.createAnswer(answer);
        }

        return newTask;
    }

    @Transactional
    public PracticalTask updateTask(PracticalTask newPracticalTask, Long id) {

        PracticalTask updatedPracticalTask = taskRepository.findById(id)
                .map(task -> {
                    task.setQuestion(newPracticalTask.getQuestion());
                    task.setValue(newPracticalTask.getValue());
                    task.setRightQuery(newPracticalTask.getRightQuery());
                    return taskRepository.save(task);
                })
                .orElseGet(() -> {
                    newPracticalTask.setTaskId(id);
                    return taskRepository.save(newPracticalTask);
                });



        return updatedPracticalTask;
    }
    
}

package com.example.demo.service;

import com.example.demo.exception.AnswerNotFoundException;
import com.example.demo.model.TestAnswer;
import com.example.demo.repository.TestAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TestAnswerService {

    @Autowired
    private TestAnswerRepository repository;


    @Autowired
    public void setRepository(TestAnswerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void deleteAnswer(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public List<TestAnswer> getAll() {
        List<TestAnswer> answerEntities = repository.findAll();
        return answerEntities;
    }

    @Transactional
    public TestAnswer getById(Long id) {
        Optional<TestAnswer> answer = repository.findById(id);
        if (answer.isPresent()){
            return answer.get();
        }
        else
            throw new AnswerNotFoundException(id);

    }

    @Transactional
    public TestAnswer createAnswer(TestAnswer newAnswer) {
        newAnswer = repository.save(newAnswer);
        return newAnswer;
    }

    @Transactional
    public TestAnswer updateAnswer(TestAnswer newTestAnswer, Long id) {

        TestAnswer updatedTestAnswer = repository.findById(id)
                .map(answer -> {
                    answer.setAnswer(newTestAnswer.getAnswer());
                    answer.setTask(newTestAnswer.getTask());
                    answer.setCorrect(newTestAnswer.getCorrect());  // TO DO!!!!!!!!
                    return repository.save(answer);
                })
                .orElseGet(() -> {
                    newTestAnswer.setAnswerId(id);
                    return repository.save(newTestAnswer);
                });

        return updatedTestAnswer;
    }

}
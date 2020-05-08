package com.example.demo.service;

import com.example.demo.exception.AnswerNotFoundException;
import com.example.demo.model.PracticalAnswer;
import com.example.demo.model.PracticalTask;
import com.example.demo.repository.PracticalAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PracticalAnswerService {

    @Autowired
    private PracticalAnswerRepository repository;


    @Autowired
    public void setRepository(PracticalAnswerRepository repository) {
        this.repository = repository;
    }


    @Transactional
    public void deleteAnswer(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public List<PracticalAnswer> getAll() {
        List<PracticalAnswer> answerEntities = repository.findAll();
        return answerEntities;
    }

    @Transactional
    public PracticalAnswer getById(Long id) {
        Optional<PracticalAnswer> answer = repository.findById(id);
        if (answer.isPresent()){
            return answer.get();
        }
        else
            throw new AnswerNotFoundException(id);

    }

    @Transactional
    public PracticalAnswer createAnswer(PracticalAnswer newAnswer) {

        newAnswer = repository.save(newAnswer);

        return newAnswer;
    }

    @Transactional
    public PracticalAnswer updateAnswer(PracticalAnswer newPracticalAnswer, Long id) {

        PracticalAnswer updatedPracticalAnswer = repository.findById(id)
                .map(answer -> {
                    answer.setCorrect(newPracticalAnswer.getCorrect());
                    answer.setTask(newPracticalAnswer.getTask());
                    answer.setUserQuery(newPracticalAnswer.getUserQuery());
                    return repository.save(answer);
                })
                .orElseGet(() -> {
                    newPracticalAnswer.setAnswerId(id);
                    return repository.save(newPracticalAnswer);
                });

        return updatedPracticalAnswer;
    }

}
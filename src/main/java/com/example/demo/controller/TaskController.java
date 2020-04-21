package com.example.demo.controller;

import com.example.demo.excpection.TaskNotFoundException;
import com.example.demo.model.AbstractTask;
import com.example.demo.repository.AbstractTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class TaskController {
    
    @Autowired
    private AbstractTaskRepository repository;
    @Autowired
    private TaskAssembler assembler;
    
    @PostMapping("/task")
    ResponseEntity<?> newAbstractTask(@RequestBody AbstractTask newAbstractTask) {

        EntityModel<AbstractTask> entityModel = assembler.toModel(repository.save(newAbstractTask));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/task/{id}")
    AbstractTask one(@PathVariable Integer id) {
        AbstractTask task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return task;
    }


    @GetMapping("/tasks")
    CollectionModel<EntityModel<AbstractTask>> all() {
        List<EntityModel<AbstractTask>> tasks = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(tasks,
                linkTo(methodOn(TaskController.class).all()).withSelfRel());
    }

    @PutMapping("/tasks/{id}")
    ResponseEntity<?> replaceAbstractTask(@RequestBody AbstractTask newAbstractTask, @PathVariable Integer id) throws URISyntaxException {

        AbstractTask updatedAbstractTask = repository.findById(id)
                .map(task -> {
                    task.setQuestion(newAbstractTask.getQuestion());
                    task.setValue(newAbstractTask.getValue());
                    return repository.save(task);
                })
                .orElseGet(() -> {
                    newAbstractTask.setTaskId(id);
                    return repository.save(newAbstractTask);
                });

        EntityModel<AbstractTask> entityModel = assembler.toModel(updatedAbstractTask);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/task/{id}")
    ResponseEntity<Object> deleteAbstractTask(@PathVariable Integer id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

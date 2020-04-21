package com.example.demo.controller;


import com.example.demo.model.AbstractTask;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
class TaskAssembler implements RepresentationModelAssembler<AbstractTask, EntityModel<AbstractTask>> {

    @Override
    public EntityModel<AbstractTask> toModel(AbstractTask task) {

        return new EntityModel<>(task,
                linkTo(methodOn(TaskController.class).one(task.getTaskId())).withSelfRel(),
                linkTo(methodOn(TaskController.class).all()).withRel("tasks"));
    }
}
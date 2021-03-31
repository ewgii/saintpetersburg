package net.nc.training.center.ta.saintpetersburg.controller;

import net.nc.training.center.ta.saintpetersburg.model.ModelTask;
import net.nc.training.center.ta.saintpetersburg.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/projects")
    // перепишется, тк нужно выводить список проектов с количеством задач в каждом
    public List<ModelTask> findAllProjects() {
        return this.taskService.findAllOpenTask();
    }

    @GetMapping("/projects/archived")
    public List<ModelTask> findAllArchivedProjects() {
        return this.taskService.findAllArchived();
    }

    @GetMapping("/projects/{name}")
    public ModelTask getModelTask(@PathVariable String name) {
        return taskService.findTask(name);
    }

    @PostMapping("/project")
    @ResponseBody
    public ResponseEntity<?> addTask(@RequestBody ModelTask modelTask) {
        taskService.addTask(modelTask);
        return new ResponseEntity<>(modelTask, HttpStatus.CREATED);
    }

    @PutMapping("/projects/{name}")
    @ResponseBody
    public ResponseEntity<?> updateTask(@RequestBody ModelTask modelTask) {
        taskService.updateTask(modelTask);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/projects/{name}")
    @ResponseBody
    public ResponseEntity<?> deleteTask(@RequestBody ModelTask modelTask) {
        taskService.deleteTask(modelTask);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

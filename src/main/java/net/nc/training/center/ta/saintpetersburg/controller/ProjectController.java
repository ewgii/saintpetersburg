package net.nc.training.center.ta.saintpetersburg.controller;

import net.nc.training.center.ta.saintpetersburg.model.Project;
import net.nc.training.center.ta.saintpetersburg.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService taskService;

    @Autowired
    public ProjectController(ProjectService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    // перепишется, тк нужно выводить список проектов с количеством задач в каждом
    public ResponseEntity<List<Project>> findOpenProjects() {
        return ok().body(taskService.findAllOpenTask());
    }

    @GetMapping("/archived")
    public List<Project> findAllArchivedProjects() {
        return this.taskService.findAllArchived();
    }

    @GetMapping("/{id}")
    public Project getModelTask(@PathVariable Long id) {
        return taskService.findTaskById(id);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> addTask(@RequestBody Project modelTask) {
        Project addTask = taskService.addTask(modelTask);
        if (addTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modelTask, HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<?> updateTask(@RequestBody Project modelTask) {
        Project updateTask = taskService.updateTask(modelTask);
        if (updateTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

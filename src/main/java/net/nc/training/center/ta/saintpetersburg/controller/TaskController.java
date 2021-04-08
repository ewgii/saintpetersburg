package net.nc.training.center.ta.saintpetersburg.controller;

import net.nc.training.center.ta.saintpetersburg.model.ModelTask;
import net.nc.training.center.ta.saintpetersburg.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/projects")
    // перепишется, тк нужно выводить список проектов с количеством задач в каждом
    public ResponseEntity<List<ModelTask>> findAllProjects() {
        return ok().body(taskService.findAllOpenTask());
    }

    @GetMapping("/projects/archived")
    public List<ModelTask> findAllArchivedProjects() {
        return this.taskService.findAllArchived();
    }

    @GetMapping("/projects/{id}")
    public ModelTask getModelTask(@PathVariable Long id) {
        return taskService.findTaskById(id);
    }

    @PostMapping("/project")
    @ResponseBody
    public ResponseEntity<?> addTask(@RequestBody ModelTask modelTask) {
        ModelTask addTask = taskService.addTask(modelTask);
        if (addTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modelTask, HttpStatus.CREATED);
    }

    @PutMapping("/projects")
    @ResponseBody
    public ResponseEntity<?> updateTask(@RequestBody ModelTask modelTask) {
        ModelTask updateTask = taskService.updateTask(modelTask);
        if (updateTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        if (taskService.findTaskById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        taskService.deleteTask(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}

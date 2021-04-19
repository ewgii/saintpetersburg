package net.nc.training.center.ta.saintpetersburg.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.nc.training.center.ta.saintpetersburg.model.Project;
import net.nc.training.center.ta.saintpetersburg.model.Task;
import net.nc.training.center.ta.saintpetersburg.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Data
    @AllArgsConstructor
    static class TasksAndProgress {
        private List<Task> taskList;
        private Long completedTasks;
        private Long allTasks;
    }

    @GetMapping("/today")
    public TasksAndProgress findTodayTask() {
        List<Task> taskList = taskService.findTasksForToday();
        Long allTasks = (long) taskService.findTasksForToday().size();
        Long completedTasks = (long) taskService.findTodayExecuteTask().size();
        return new TasksAndProgress(taskList, completedTasks, allTasks);
    }

    @GetMapping("/future")
    public List<Task> findFutureTask() {
        return taskService.findFutureTasks();
    }

    @GetMapping("/{date}")
    public List<Task> findTasksForDate(@PathVariable LocalDate date) {
        return taskService.findTasksForDate(date);
    }

    @GetMapping("/input")
    public ResponseEntity<List<Task>> findTasksWithoutProject() {
        return new ResponseEntity<>(taskService.findTaskWithoutProject(), HttpStatus.OK);
    }

    @GetMapping("/project")
    public TasksAndProgress findTasksOfProject(@RequestBody Project project) {
        List<Task> taskList = taskService.findProjectTasks(project);
        Long allTasks = (long) taskService.findProjectTasks(project).size();
        Long completedTasks = (long) taskService.findExecuteProjectTasks(project).size();
        return new TasksAndProgress(taskList, completedTasks, allTasks);
    }

        @GetMapping("/task/{id}")
    public ResponseEntity<Optional<Task>> findTask(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.findTask(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> addTask(@RequestBody Task task) {
        taskService.addTask(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<?> updateTask(@RequestBody Task task) {
        taskService.updateTask(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}

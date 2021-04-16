package net.nc.training.center.ta.saintpetersburg.controller;

import lombok.RequiredArgsConstructor;
import net.nc.training.center.ta.saintpetersburg.model.Project;
import net.nc.training.center.ta.saintpetersburg.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    // перепишется, тк нужно выводить список проектов с количеством задач в каждом
    public ResponseEntity<List<Project>> findOpenProjects() {
        return ok().body(projectService.findAllOpenProjects());
    }

    @GetMapping("/archived")
    public List<Project> findAllArchivedProjects() {
        return this.projectService.findAllArchived();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProject(@PathVariable Long id) {
        return new ResponseEntity<>(projectService.findProjectById(id), HttpStatus.CREATED);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> addProject(@RequestBody Project project) {
        projectService.addProject(project);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<?> updateProject(@RequestBody Project project) {
        projectService.updateProject(project);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}

package net.nc.training.center.ta.saintpetersburg.service;

import lombok.RequiredArgsConstructor;
import net.nc.training.center.ta.saintpetersburg.model.Project;
import net.nc.training.center.ta.saintpetersburg.model.Task;
import net.nc.training.center.ta.saintpetersburg.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> findTasksForToday() {
        return taskRepository.findDistinctByDeadlineOrDeadlineBeforeAndDoneFalse(
                LocalDate.now(), LocalDate.now());
    }

    public List<Task> findTodayExecuteTask() {
        return taskRepository.findTasksByExecuteDate(LocalDate.now());
    }

    public List<Task> findFutureTasks() {
        return taskRepository.findTaskByDeadlineAfter(LocalDate.now());
    }

    public List<Task> findTaskWithoutProject() {
        return taskRepository.findTasksByProject(null);
    }

    public List<Task> findProjectTasks(Project project) {
        return taskRepository.findTasksByProject(project);
    }

    public List<Task> findExecuteProjectTasks(Project project) {
        return taskRepository.findTasksByProjectAndDone(project, true);
    }

    public Optional<Task> findTask(Long id) {
        return taskRepository.findById(id);
    }

    public void addTask(Task task) {
        task.setDone(false);
        taskRepository.save(task);
    }

    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> findTasksForDate(LocalDate date) {
        return taskRepository.findTasksByDeadline(date);
    }


}

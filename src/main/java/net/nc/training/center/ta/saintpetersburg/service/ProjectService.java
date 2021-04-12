package net.nc.training.center.ta.saintpetersburg.service;

import net.nc.training.center.ta.saintpetersburg.model.Project;
import net.nc.training.center.ta.saintpetersburg.model.StatusTask;
import net.nc.training.center.ta.saintpetersburg.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository taskRepository;

    @Autowired
    public ProjectService(ProjectRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public List<Project> findAllOpenTask() {
        return taskRepository.findAllByStatusEquals(StatusTask.OPEN);
    }

    public List<Project> findAllArchived() {
        return taskRepository.findAllByStatusEquals(StatusTask.ARCHIVED);
    }

    public Project findTaskById(Long id) {
        return taskRepository.findModelTaskById(id);
    }

    public Project addTask(Project modelTask) {
        Project modelTaskFromDB = taskRepository.findModelTaskByName(modelTask.getName());
        if (modelTaskFromDB != null) {
            return null;
        }
        modelTask.setCreateDate(LocalDate.now());
        modelTask.setStatus(StatusTask.OPEN);
        return taskRepository.save(modelTask);
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Project updateTask(Project modelTask) {
        Project modelTaskFromDB = taskRepository.findModelTaskById(modelTask.getId());
        if (modelTaskFromDB == null) {
            return null;
        }
        return taskRepository.save(modelTask);
    }
}

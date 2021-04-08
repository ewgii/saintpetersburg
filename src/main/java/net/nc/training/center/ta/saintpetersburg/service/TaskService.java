package net.nc.training.center.ta.saintpetersburg.service;

import net.nc.training.center.ta.saintpetersburg.model.ModelTask;
import net.nc.training.center.ta.saintpetersburg.model.StatusTask;
import net.nc.training.center.ta.saintpetersburg.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public List<ModelTask> findAllOpenTask() {
        return taskRepository.findAllByStatusEquals(StatusTask.OPEN);
    }

    public List<ModelTask> findAllArchived() {
        return taskRepository.findAllByStatusEquals(StatusTask.ARCHIVED);
    }

    public ModelTask findTaskById(Long id) {
        return taskRepository.findModelTaskById(id);
    }

    public ModelTask addTask(ModelTask modelTask) {
        ModelTask modelTaskFromDB = taskRepository.findModelTaskByName(modelTask.getName());
        if (modelTaskFromDB != null) {
            return null;
        }
        modelTask.setCreate_date(LocalDate.now());
        return taskRepository.save(modelTask);
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteModelTaskById(id);
    }

    public ModelTask updateTask(ModelTask modelTask) {
        ModelTask modelTaskFromDB = taskRepository.findModelTaskById(modelTask.getId());
        if (modelTaskFromDB == null) {
            return null;
        }
        return taskRepository.save(modelTask);
    }
}

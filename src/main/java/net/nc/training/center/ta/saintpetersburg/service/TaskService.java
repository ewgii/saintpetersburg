package net.nc.training.center.ta.saintpetersburg.service;

import net.nc.training.center.ta.saintpetersburg.model.ModelTask;
import net.nc.training.center.ta.saintpetersburg.model.StatusTask;
import net.nc.training.center.ta.saintpetersburg.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
        return taskRepository.findAllByStatusEquals(StatusTask.CLOSE);
    }

    public ModelTask findTask(String name) {
        return taskRepository.findModelTaskByName(name);
    }

    public void addTask(ModelTask modelTask) {
        ModelTask modelTaskFromDB = taskRepository.findModelTaskByName(modelTask.getName());

        if (modelTaskFromDB != null) {
            return;
        }

        modelTask.setCreate_date(LocalDate.now());
        taskRepository.save(modelTask);
    }

    public void deleteTask(ModelTask modelTask) {
        taskRepository.delete(modelTask);
    }

    public void updateTask(ModelTask modelTask) {
        ModelTask modelTaskFromDB = taskRepository.findModelTaskByName(modelTask.getName());

        if (modelTaskFromDB == null) {
            return;
        }

        // if для переноса задачи в архив
        if (modelTask.getStatus().equals(StatusTask.ARCHIVED)) {
            modelTaskFromDB.setStatus(StatusTask.ARCHIVED);
            modelTaskFromDB.setDate_archived(LocalDate.now());
            return;
        }

        // if для переноса задачи из архива
        if (modelTask.getStatus().equals(StatusTask.OPEN)
                && modelTaskFromDB.getStatus().equals(StatusTask.ARCHIVED)) {
            modelTaskFromDB.setStatus(StatusTask.OPEN);
            modelTaskFromDB.setDate_archived(LocalDate.now());
            return;
        }

        // if для закрытия задачи
        if (modelTask.getStatus().equals(StatusTask.CLOSE)) {
            modelTaskFromDB.setStatus(StatusTask.CLOSE);
            return;
        }

        modelTaskFromDB.setName(modelTask.getName());
        modelTaskFromDB.setDescription(modelTask.getDescription());
        modelTaskFromDB.setDeadline(modelTask.getDeadline());

        taskRepository.save(modelTaskFromDB);
    }
}

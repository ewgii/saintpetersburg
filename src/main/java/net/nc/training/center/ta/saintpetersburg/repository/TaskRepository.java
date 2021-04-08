package net.nc.training.center.ta.saintpetersburg.repository;

import net.nc.training.center.ta.saintpetersburg.model.ModelTask;
import net.nc.training.center.ta.saintpetersburg.model.StatusTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<ModelTask, String> {

    void deleteModelTaskById(Long id);

    ModelTask findModelTaskById(Long id);

    ModelTask findModelTaskByName(String name);

    List<ModelTask> findAllByStatusEquals(StatusTask status);
}

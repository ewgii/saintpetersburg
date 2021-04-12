package net.nc.training.center.ta.saintpetersburg.repository;

import net.nc.training.center.ta.saintpetersburg.model.Project;
import net.nc.training.center.ta.saintpetersburg.model.StatusTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findModelTaskById(Long id);

    Project findModelTaskByName(String name);

    List<Project> findAllByStatusEquals(StatusTask status);
}

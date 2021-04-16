package net.nc.training.center.ta.saintpetersburg.repository;

import net.nc.training.center.ta.saintpetersburg.model.Project;
import net.nc.training.center.ta.saintpetersburg.model.StatusProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findProjectById(Long id);

    Project findProjectByName(String name);

    List<Project> findAllByStatusEquals(StatusProject status);
}

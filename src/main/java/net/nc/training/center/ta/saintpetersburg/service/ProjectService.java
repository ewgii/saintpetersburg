package net.nc.training.center.ta.saintpetersburg.service;

import lombok.RequiredArgsConstructor;
import net.nc.training.center.ta.saintpetersburg.model.Project;
import net.nc.training.center.ta.saintpetersburg.model.StatusProject;
import net.nc.training.center.ta.saintpetersburg.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> findAllOpenProjects() {
        return projectRepository.findAllByStatusEquals(StatusProject.OPEN);
    }

    public List<Project> findAllArchived() {
        return projectRepository.findAllByStatusEquals(StatusProject.ARCHIVED);
    }

    public Project findProjectById(Long id) {
        return projectRepository.findProjectById(id);
    }

    public void addProject(Project project) {
        project.setCreateDate(LocalDate.now());
        project.setStatus(StatusProject.OPEN);
        projectRepository.save(project);
    }

    @Transactional
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public void updateProject(Project project) {
        projectRepository.save(project);
    }
}

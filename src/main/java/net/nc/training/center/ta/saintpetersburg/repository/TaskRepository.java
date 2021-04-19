package net.nc.training.center.ta.saintpetersburg.repository;

import net.nc.training.center.ta.saintpetersburg.model.Project;
import net.nc.training.center.ta.saintpetersburg.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findDistinctByDeadlineOrDeadlineBeforeAndDoneFalse(LocalDate deadline, LocalDate beforeDeadline);

    List<Task> findTasksByExecuteDate(LocalDate executeDate);

    List<Task> findTaskByDeadlineAfter(LocalDate deadline);

    List<Task> findTasksByProject(Project project);

    List<Task> findTasksByProjectAndDone(Project project, Boolean done);

    List<Task> findTasksByDeadline(LocalDate localDate);

}

package net.nc.training.center.ta.saintpetersburg.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50)
    private String name;

    @Column
    private String description;

    @Column
    private LocalDate deadline;

    @Column(name = "execute_date")
    private LocalDate executeDate;

    @Column
    private boolean done;

    @OneToMany
    @JoinColumn(name = "parent_id")
    private Set<Task> childs;


    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}


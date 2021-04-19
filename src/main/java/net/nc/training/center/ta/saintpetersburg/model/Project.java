package net.nc.training.center.ta.saintpetersburg.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50)
    private String name;

    @Column
    private String description;

    @Column(updatable = false, name = "create_date")
    private LocalDate createDate;

    @Column
    private LocalDate deadline;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusProject status;

    @Column(name = "date_archived")
    private LocalDate dateArchived;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "project")
    protected Set<Task> projectTasks = new HashSet<>();
}

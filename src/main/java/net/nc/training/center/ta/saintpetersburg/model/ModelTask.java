package net.nc.training.center.ta.saintpetersburg.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "task")
public class ModelTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50)
    private String name;

    @Column
    private String description;

    @Column(updatable = false)
    private LocalDate create_date;

    @Column
    private LocalDate deadline;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusTask status;

    @Column
    private LocalDate date_archived;

    @ManyToOne
    @JoinColumn (name="user_id")
    private ModelUser user;
}

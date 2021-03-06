package net.nc.training.center.ta.saintpetersburg.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String login;

    @Column
    private String password;

    @OneToMany(mappedBy = "user")
    protected Set<Project> userProjects = new HashSet<>();
}

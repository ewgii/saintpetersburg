package net.nc.training.center.ta.saintpetersburg.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "usr")
public class ModelUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String login;

    @Column
    private String password;
}

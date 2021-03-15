package net.nc.training.center.ta.saintpetersburg.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "temp_table")
public class TempModelMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

}

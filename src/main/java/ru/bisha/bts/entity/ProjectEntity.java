package ru.bisha.bts.entity;

import java.util.List;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "projects")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<TaskEntity> tasks;
}

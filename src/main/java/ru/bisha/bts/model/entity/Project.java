package ru.bisha.bts.model.entity;

import java.util.List;
import lombok.Data;
import ru.bisha.bts.model.entity.Task;

import javax.persistence.*;

@Data
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;
}

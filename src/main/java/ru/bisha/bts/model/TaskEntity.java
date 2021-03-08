package ru.bisha.bts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "theme")
    private String theme;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "executor_id")
    private UserEntity executor;

    @Column(name = "description")
    private String description;

    public enum Type {
        BUG, TASK, ISSUE
    }

    public enum Priority {
        LOW, MEDIUM, HIGH
    }
}

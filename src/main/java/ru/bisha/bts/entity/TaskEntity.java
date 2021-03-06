package ru.bisha.bts.entity;

import lombok.Data;

@Data
public class TaskEntity {

    private int id;

    private String theme;

    private ProjectEntity project;

    private Type type;

    private Priority priority;

    private UserEntity executor;

    private String description;

    public enum Type {
        BUG, TASK, ISSUE
    }

    public enum Priority  {
        LOW, MEDIUM, HIGH
    }
}

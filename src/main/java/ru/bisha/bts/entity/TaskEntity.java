package ru.bisha.bts.entity;

import lombok.Data;

@Data
public class TaskEntity {

    private int id;

    private ProjectEntity project;

    private String theme;

    private Type type;

    private Priority priority;

    private UserEntity executor;

    private String description;

    public static enum Type {
        BUG, TASK, ISSUE
    }

    public static enum Priority  {
        LOW, MEDIUM, HIGH
    }
}

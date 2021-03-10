package ru.bisha.bts.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskDto {

    private int id;

    private String theme;

    private ProjectDto project;

    private Type type;

    private Priority priority;

    private UserDto executor;

    private String description;

    public enum Type {
        BUG, TASK, ISSUE
    }

    public enum Priority {
        LOW, MEDIUM, HIGH
    }
}

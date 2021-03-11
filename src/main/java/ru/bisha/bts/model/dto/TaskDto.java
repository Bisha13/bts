package ru.bisha.bts.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bisha.bts.model.entity.Task;

@Data
@NoArgsConstructor
public class TaskDto {

    private int id;

    private String theme;

    private int project;

    private Task.Type type;

    private Task.Priority priority;

    private int executor;

    private String description;

    private static final String SEP = "|";

    public TaskDto(int id, String theme,
                   int project, Task.Type type, Task.Priority priority,
                   int executor, String description) {
        this.id = id;
        this.theme = theme;
        this.project = project;
        this.type = type;
        this.priority = priority;
        this.executor = executor;
        this.description = description;
    }

    @Override
    public String toString() {
        return "task" + SEP
                +  id + SEP
                + theme + SEP
                + project + SEP
                + type + SEP
                + priority + SEP
                + executor + SEP
                + description;
    }
}

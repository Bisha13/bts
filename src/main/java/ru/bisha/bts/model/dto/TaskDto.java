package ru.bisha.bts.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bisha.bts.model.entity.Task;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private int id;

    private String theme;

    private int project;

    private Task.Type type;

    private Task.Priority priority;

    private int executor;

    private String description;

}

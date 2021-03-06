package ru.bisha.bts.entity;

import java.util.List;
import lombok.Data;

@Data
public class ProjectEntity {

    private int id;

    private String name;

    private List<TaskEntity> tasks;
}

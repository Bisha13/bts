package ru.bisha.bts.entity;

import lombok.Data;

import java.util.List;

@Data
public class ProjectEntity {

    private int id;

    private String name;

    private List<TaskEntity> tasks;
}

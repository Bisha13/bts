package ru.bisha.bts.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserEntity {

    private int id;

    private List<TaskEntity> tasks;

}

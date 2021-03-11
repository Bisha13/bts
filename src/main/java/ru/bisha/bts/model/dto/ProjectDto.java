package ru.bisha.bts.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

    private int id;

    private String name;

    private static final String SEP = "|";

    @Override
    public String toString() {
        return "project" + SEP
                +  id + SEP
                + name;
    }
}

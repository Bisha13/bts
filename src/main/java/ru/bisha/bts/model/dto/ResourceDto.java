package ru.bisha.bts.model.dto;

import lombok.Data;

@Data
public class ResourceDto {

    private String resource;

    public String getResource() {
        return "src/main/resources/data/" + resource;
    }
}

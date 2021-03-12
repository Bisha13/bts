package ru.bisha.bts.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int id;

    private String name;

    private static final String SEP = "|";

    @Override
    public String toString() {
        return "user" + SEP
                +  id + SEP
                + name;
    }

}

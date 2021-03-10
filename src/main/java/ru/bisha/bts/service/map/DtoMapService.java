package ru.bisha.bts.service.map;

import org.springframework.stereotype.Service;
import ru.bisha.bts.model.dto.UserDto;
import ru.bisha.bts.model.entity.User;

@Service
public class DtoMapService {

    public UserDto mapToDto(User user) {
        return new UserDto(user.getId(), user.getName());
    }
}

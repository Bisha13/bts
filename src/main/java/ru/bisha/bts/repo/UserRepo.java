package ru.bisha.bts.repo;

import org.springframework.data.repository.CrudRepository;
import ru.bisha.bts.entity.UserEntity;

public interface UserRepo extends CrudRepository<UserEntity, Integer> {
}

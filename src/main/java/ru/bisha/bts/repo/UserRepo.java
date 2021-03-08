package ru.bisha.bts.repo;

import org.springframework.data.repository.CrudRepository;
import ru.bisha.bts.model.User;

public interface UserRepo extends CrudRepository<User, Integer> {
}

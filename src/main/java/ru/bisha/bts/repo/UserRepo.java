package ru.bisha.bts.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.bisha.bts.entity.UserEntity;

public interface UserRepo extends CrudRepository<UserEntity, Integer> {

    @Modifying
    @Query(
            value = "truncate table users",
            nativeQuery = true
    )
    void truncateMyTable();
}

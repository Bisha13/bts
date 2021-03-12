package ru.bisha.bts.service.sdjpa;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bisha.bts.model.entity.User;
import ru.bisha.bts.repo.UserRepo;
import ru.bisha.bts.service.UserService;


@Service
public class UserSDJpaService implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User findById(final Integer id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User save(final User object) {
        return userRepo.save(object);
    }

    @Override
    public void delete(final User object) {
        userRepo.delete(object);
    }

    @Override
    public void deleteById(final Integer id) {
        userRepo.deleteById(id);
    }
}

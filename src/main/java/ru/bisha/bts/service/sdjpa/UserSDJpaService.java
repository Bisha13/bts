package ru.bisha.bts.service.sdjpa;

import java.util.HashSet;
import java.util.Set;
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
    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        userRepo.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User findById(Integer id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User save(User object) {
        return userRepo.save(object);
    }

    @Override
    public void delete(User object) {
        userRepo.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        userRepo.deleteById(id);
    }
}

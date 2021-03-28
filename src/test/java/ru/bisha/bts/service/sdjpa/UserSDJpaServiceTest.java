package ru.bisha.bts.service.sdjpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bisha.bts.model.entity.User;
import ru.bisha.bts.repo.UserRepo;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class UserSDJpaServiceTest {



        @Mock
        UserRepo userRepo;

        @InjectMocks
        UserSDJpaService service;

        @Test
        void testFindAll() {
            service.findAll();
            verify(userRepo, times(1)).findAll();
        }

        @Test
        void testFindById() {
            service.findById(1);
            verify(userRepo, times(1))
                    .findById(anyInt());
        }

        @Test
        void testDelete() {
            service.delete(new User());
            verify(userRepo, times(1))
                    .delete(any(User.class));
        }

        @Test
        void testDeleteById() {
            service.deleteById(1);
            verify(userRepo, times(1))
                    .deleteById(anyInt());
        }
}
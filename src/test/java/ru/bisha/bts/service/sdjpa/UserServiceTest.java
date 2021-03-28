package ru.bisha.bts.service.sdjpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.bisha.bts.model.entity.Task;
import ru.bisha.bts.model.entity.User;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User persistence test")
class UserServiceTest extends PersistenceTest {

    @Autowired
    UserSDJpaService userService;

    @Autowired
    TaskSDJpaService taskService;

    @DisplayName("Test findAll + save")
    @Test
    void findAll() {

        List<User> userBefore = userService.findAll();
        int size = userBefore.size();

        Set<String> expected = new HashSet<>();
        expected.add("1|Ivan-test");
        expected.add("2|Darya-test");
        expected.add("3|Igor-test");

        Set<String> actual = userBefore
                .stream()
                .map(u -> u.getId() + "|" + u.getName())
                .collect(Collectors.toSet());

        assertEquals(expected, actual);

        User user1 = new User();
        user1.setName("Name1");
        User user2 = new User();
        user2.setName("Name2");
        User user3 = new User();
        user3.setName("Name3");
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);

        assertNotEquals(0, userBefore.size());
        List<User> usersAfter = userService.findAll();

        expected.add("4|Name1");
        expected.add("5|Name2");
        expected.add("6|Name3");

        Set<String> actual2 = usersAfter
                .stream()
                .map(u -> u.getId() + "|" + u.getName())
                .collect(Collectors.toSet());

        assertEquals(expected, actual2);

        int sizeAfter = usersAfter.size();
        assertEquals(size + 3,  sizeAfter);


    }

    @Transactional // I dont like it here ether.
    @DisplayName("Test find all tasks of the user")
    @Test
    void findTasksOfUser() {
        List<Task> expectedTaskForId1 = new ArrayList<>();

        //There are tasks 1 and 5 in test.csv for user id 1.
        expectedTaskForId1.add(taskService.findById(1));
        expectedTaskForId1.add(taskService.findById(5));

        List<Task> actualListForId1
                = userService.findById(1).getTasks();
        assertEquals(expectedTaskForId1, actualListForId1);
    }

}
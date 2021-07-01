package com.epam.brest.dao;

import com.epam.brest.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DaoTest {

    User user = new User("User"+((int)(Math.random()*1000000)), "Password123");

    @Test
    void createNewUserTest() {
        Dao dao = new Dao("src/test/resources/test.txt");
        Assertions.assertTrue(dao.createNewUser(user));
    }

    @Test
    void ifNotFoundFileTest() {
        Dao dao = new Dao("src/test/resources/test2.txt");
        Assertions.assertFalse(dao.createNewUser(user));
    }

    @Test
    void ifTheSameLoginTest() {
        Dao dao = new Dao("src/test/resources/test.txt");
        Assertions.assertFalse(dao.createNewUser(new User("Aleh", "Password111")));
    }

    @Test
    void deleteUserTest() {
        Dao dao = new Dao("src/test/resources/test.txt");
        User testUser = new User("testUser", "testPassword111");
        dao.createNewUser(testUser);
        Assertions.assertTrue(dao.deleteUser(testUser));
    }

    @Test
    void ifWrongLoginTest() {
        Dao dao = new Dao("src/test/resources/test.txt");
        User testUser = new User("Aleh2", "Password111");
        Assertions.assertFalse(dao.deleteUser(testUser));
    }

    @Test
    void ifWrongPasswordTest() {
        Dao dao = new Dao("src/test/resources/test.txt");
        User testUser = new User("Aleh", "wrongPassword111");
        Assertions.assertFalse(dao.deleteUser(testUser));
    }
}
package com.epam.brest.dao;

import com.epam.brest.entity.User;

import java.io.*;
import java.util.*;

public class Dao {

    private String path;

    public Dao() {
        this.path = "authorization/src/main/resources/users.txt";
    }

    public Dao(String path) {
        this.path = path;
    }

    public void createNewUser() {

    }

    public void deleteUser(String userPassword) {

    }

    private List<User> showAllUsers() {
        List<User> users = new ArrayList<>();

        return users;
    }

}

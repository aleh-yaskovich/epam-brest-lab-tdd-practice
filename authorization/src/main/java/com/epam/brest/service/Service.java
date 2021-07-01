package com.epam.brest.service;

import com.epam.brest.dao.Dao;
import com.epam.brest.entity.User;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Service {

    private final Dao dao = new Dao();

    private final String[] menuItems = {
            "Press '0' - to finish program",
            "Press '1' - to create new user",
            "Press '2' - to delete user"
    };

    public void menu() {
        for(String str : menuItems)
            System.out.println(str);
        int i = selectMenuItem(menuItems);

        while(i != 0) {

            if(i == 1) {createNewUser();}
            else if (i == 2) {deleteUser();}

            for(String str : menuItems)
                System.out.println(str);
            i = selectMenuItem(menuItems);
        }
    }

    /**
     * user enters login and password
     * if login is unique and true, and password is also true, new user is added
     * else the message appears
     */
    public void createNewUser() {
        System.out.println("CREATE NEW USER");
        System.out.println("Login must be from 4 to 30 characters, and can include letters, numbers and character '_'");
        System.out.println("Password must be from 8 to 30 characters, and must include only uppercase and lowercase letters, as well as numbers");
        String userName = userData("Enter login: ").trim();
        while(!checkLogin(userName)) {
            userName = userData("Enter login: ").trim();
        }
        String userPassword = userData("Enter password: ").trim();
        while(!checkLogin(userPassword)) {
            userPassword = userData("Enter password: ").trim();
        }
        dao.createNewUser(new User(userName, encode(userPassword)));
    }

    /**
     * user enters login and password
     * if login and password are true, user is deleted
     * else the message appears
     */
    public void deleteUser() {
        System.out.println("DELETE USER");
        String userName = userData("Enter login: ").trim();
        String userPassword = userData("Enter password: ").trim();
        dao.deleteUser(new User(userName, encode(userPassword)));
    }

    /**
     * userLogin must be from 4 to 30 characters and might contain letters, numbers and character '_'
     * userLogin must be unique
     */
    public boolean checkLogin(String userLogin) {
        return userLogin.matches("^\\w{4,30}$");
    }

    /**
     * userPassword must be from 8 to 30 characters and contain uppercase and lowercase letters, and number(s)
     */
    public boolean checkPassword(String userPassword) {
        return userPassword.matches("[a-zA-Z0-9]{8,30}");
    }

    /**
     * get information about login or password from user
     */
    private String userData(String information) {
        Scanner sc = new Scanner(System.in);
        System.out.print(information);
        return sc.nextLine();
    }

    /**
     * get information about login or password from user
     */
    private int selectMenuItem(String[] menuItems) {
        Scanner sc = new Scanner(System.in);
        String information = "Please enter a number from 0 to " + (menuItems.length - 1) + ": ";
        System.out.print(information);
        while(!sc.hasNextInt()) {
            sc.next();
            System.out.print(information);
        }
        int i = sc.nextInt();
        while (i < 0 || i >= menuItems.length) {
            System.out.print(information);
            while(!sc.hasNextInt()) {
                sc.next();
                System.out.print(information);
            }
            i = sc.nextInt();
        }
        return i;
    }

    /**
     * encode password
     */
    private String encode(String password) {
        byte[] bytes = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(password.getBytes("utf-8"));
            bytes = messageDigest.digest();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        BigInteger encrypted = new BigInteger(1, bytes);
        return encrypted.toString();
    }

 }

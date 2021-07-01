package com.epam.brest.service;

import java.util.Scanner;

public class Service {



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
        String data = sc.nextLine();
        return data;
    }

}

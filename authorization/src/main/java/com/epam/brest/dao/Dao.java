package com.epam.brest.dao;

import com.epam.brest.entity.User;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Dao {

    private final String path;

    public Dao() {
        this.path = "authorization/src/main/resources/users.txt";
    }

    public Dao(String path) {
        this.path = path;
    }

    /**
     * if userName is unique, method adds new user to userList
     * and send userList to write to a text file
     */
    public boolean createNewUser(User newUser) {
        try {
            List<User> users = showAllUsers();
            boolean res = false;
            if(!users.isEmpty()) {
                for(User user : users) {
                    if(newUser.getUserName().equals(user.getUserName())) {
                        res = true;
                        break;
                    }
                }
                if(res) {
                    System.out.println("User with the same login already exists");
                    return false;
                }
            }
            users.add(newUser);
            if(updateTextFile(users)) {
                System.out.println("New user \""+newUser.getUserName()+"\" added successfully");
            } else {
                System.out.println("New user is not added, something went wrong");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Text-file is not found");
            return false;
        }
        return true;
    }

    /**
     * if data is true, method removes user from userList
     * and send userList to write to a text file
     */
    public boolean deleteUser(User user) {
        try {
            List<User> users = showAllUsers();
            if(!users.isEmpty()) {
                if (users.remove(user)) {
                    if(updateTextFile(users)) {
                        System.out.println("User \""+user.getUserName()+"\" deleted successfully");
                    } else {
                        System.out.println("User \""+user.getUserName()+"\" is not deleted, something went wrong");
                        return false;
                    }
                } else {
                    System.out.println("User \""+user.getUserName()+"\" is not deleted.");
                    boolean res = false;
                    for(User u : users) {
                        if(user.getUserName().equals(u.getUserName())) {
                            System.out.println("It is wrong password for user \""+user.getUserName()+"\"");
                            res = true;
                            break;
                        }
                    }
                    if (!res)
                        System.out.println("User with login \""+user.getUserName()+"\" is not found");
                    return false;
                }
            } else {
                System.out.println("There is no any created user");
                return false;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Text-file is not found");
            return false;
        }
        return true;
    }

    /**
     * get data from a text-file, create User-object and add it to usersList
     */
    private List<User> showAllUsers() throws FileNotFoundException {
        List<User> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(line != null) {
            String[] userInfo = line.split("-");
            users.add(new User(userInfo[0], userInfo[1]));
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    /**
     * method writes update userList to text file
     */
    private boolean updateTextFile(List<User> users) {
        try (
                FileWriter writer = new FileWriter(path, false)
        ) {
            for(User user : users) {
                String userString = user.getUserName()+"-"+user.getUserPassword()+"\n";
                writer.write(userString);
            }
            writer.flush();
            return true;
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

}

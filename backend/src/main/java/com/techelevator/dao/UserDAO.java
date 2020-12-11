package com.techelevator.dao;

import com.techelevator.model.User;

import java.util.List;

public interface UserDAO {

    void updateUser(User user, String username);

    Long getUserIDByName(String firstName, String lastName);

    User findByUsername(String username);

    boolean create(String username, String password, String role, String firstName, String lastName, String email, String avatar);
}

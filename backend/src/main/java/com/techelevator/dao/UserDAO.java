package com.techelevator.dao;

import com.techelevator.model.User;

import java.util.List;

public interface UserDAO {

    User findByUsername(String username);

    boolean create(String username, String password, String role, String firstName, String lastName, String email, String avatar);
}

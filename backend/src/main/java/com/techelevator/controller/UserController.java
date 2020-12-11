package com.techelevator.controller;

import com.techelevator.dao.UserDAO;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class UserController {

    private UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping(path = "/profile")
    public User getUserForProfile(Principal principal) {
        return userDAO.findByUsername(principal.getName());
    }

    @PutMapping(path= "/profile/{id}")
    public void updateProfile(@PathVariable Long id, @Valid @RequestBody User user, Principal principal) {
        Long managerID = userDAO.getUserIDByName(user.getManagerFirstName(), user.getManagerLastName());
        user.setManagerID(managerID);
        userDAO.updateUser(user, principal.getName(),id);
    }
    @GetMapping( path = "/profile/{id}")
    public List<User> getAllUsers(@PathVariable Long id) {
        return userDAO.getAllUsers(id);
    }

}

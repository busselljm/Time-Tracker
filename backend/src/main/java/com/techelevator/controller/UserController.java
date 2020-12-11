package com.techelevator.controller;

import com.techelevator.dao.UserDAO;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

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

    @PutMapping(path= "/profile")
    public void updateProfile(@Valid @RequestBody User user, Principal principal) {
        Long managerID = userDAO.getUserIDByName(user.getManagerFirstName(), user.getManagerLastName());
        user.setManagerID(managerID);
        userDAO.updateUser(user, principal.getName());
    }


}

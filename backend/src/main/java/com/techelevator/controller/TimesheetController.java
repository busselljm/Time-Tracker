package com.techelevator.controller;

import com.techelevator.dao.TimesheetDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.Timesheet;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Time;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class TimesheetController {
    private final TimesheetDAO timesheetDAO;
    private final UserDAO userDAO;
    public TimesheetController(TimesheetDAO timesheetDAO, UserDAO userDAO){
        this.timesheetDAO = timesheetDAO;
        this.userDAO = userDAO;
    }
    @GetMapping("/times/{id}")
    public Timesheet getTimesheetById(@PathVariable Long id){
        return timesheetDAO.getTimesheet(id);
    }

    @GetMapping("/times")
    public List <Timesheet> getAllTimesheets(Principal principal){
        return timesheetDAO.listTimesheets(userDAO.findByUsername(principal.getName()).getId());
    }
    @PostMapping("/times")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewTimesheet(@RequestBody Timesheet newTimesheet, Principal principal){
       newTimesheet.setUserID(userDAO.findByUsername(principal.getName()).getId());
        timesheetDAO.createTimesheet(newTimesheet);
    }

    @DeleteMapping("/times/{id}")
    public void deleteTimesheet(@PathVariable Long id){
        timesheetDAO.deleteTimesheet(id);
    }
}

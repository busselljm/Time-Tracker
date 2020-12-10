package com.techelevator.controller;

import com.techelevator.dao.TimesheetDAO;
import com.techelevator.model.Timesheet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class TimesheetController {
    private final TimesheetDAO timesheetDAO;

    public TimesheetController(TimesheetDAO timesheetDAO){
        this.timesheetDAO = timesheetDAO;

    }
    @GetMapping("/time/{id}")
    public Timesheet getTimesheetById(@PathVariable Long id){
        return timesheetDAO.getTimesheet(id);
    }
}

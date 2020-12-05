package com.techelevator.dao;

import com.techelevator.model.Timesheet;

public interface TimesheetDAO {

    Timesheet createTimesheet();

    void updateTimesheet();

    void deleteTimesheet();

}

package com.techelevator.dao;

import com.techelevator.model.Timesheet;

import java.util.List;

public interface TimesheetDAO {

    void createTimesheet(Timesheet newTimesheet);

    void updateTimesheet(Long timeId, Timesheet updatedTimesheet);

    void deleteTimesheet(Long timeId);

    List<Timesheet> listTimesheets(Long userId);

    Timesheet getTimesheet(Long timeID);

}

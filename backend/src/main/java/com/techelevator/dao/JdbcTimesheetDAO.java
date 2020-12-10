package com.techelevator.dao;

import com.techelevator.model.Timesheet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcTimesheetDAO implements TimesheetDAO{
    private JdbcTemplate jdbcTemplate;

    public JdbcTimesheetDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createTimesheet(Timesheet newTimesheet) {
        String sql = "INSERT INTO timesheet (project_id, user_id, time_desc, beginning_time, ending_time)\n" +
                "VALUES (?, ?, ?, ?, ?) RETURNING time_id;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, newTimesheet.getProjectID(), newTimesheet.getUserID(), newTimesheet.getDescription(),
                newTimesheet.getBeginningTime(), newTimesheet.getEndingTime());
        if(rowSet.next()){
            newTimesheet.setProjectID(rowSet.getLong("time_id"));
        }

    }

    @Override
    public void updateTimesheet(Long timeId, Timesheet updatedTimesheet) {

    }

    @Override
    public void deleteTimesheet(Long timeId) {

    }

    @Override
    public List<Timesheet> listTimesheets(Long userId) {
        List <Timesheet> result = new ArrayList<>();
        String sql = "SELECT project_id, user_id, time_id, time_desc, beginning_time, ending_time FROM timesheet WHERE user_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

        while(rowSet.next()){
            Timesheet timesheet = mapRowToTimesheet(rowSet);
            result.add(timesheet);
        }
        return result;
    }

    @Override
    public Timesheet getTimesheet(Long timeID) {
        String sql = "SELECT project_id, user_id, time_id, time_desc, beginning_time, ending_time FROM timesheet WHERE time_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, timeID);
        if(rowSet.next()){
            return mapRowToTimesheet(rowSet);
        }else
        return null;
    }

    private Timesheet mapRowToTimesheet(SqlRowSet rowSet) {
        Timesheet result = new Timesheet();
//        result.setBeginningTime(LocalDateTime.parse(rowSet.getString("beginning_time"), DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss" )));
        result.setBeginningTime(rowSet.getTimestamp("beginning_time").toLocalDateTime());
        result.setDescription(rowSet.getString("time_desc"));
        result.setEndingTime(rowSet.getTimestamp("ending_time").toLocalDateTime());
        result.setProjectID(rowSet.getLong("project_id"));
        result.setUserID(rowSet.getLong("user_id"));
        result.setTimeID(rowSet.getLong("time_id"));

        return result;
    }
}

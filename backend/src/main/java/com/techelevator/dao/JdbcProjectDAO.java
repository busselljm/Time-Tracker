package com.techelevator.dao;

import com.techelevator.model.Project;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.time.LocalDate;

public class JdbcProjectDAO implements ProjectDAO{

    private JdbcTemplate jdbcTemplate;

    public JdbcProjectDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Project createProject(long projectID, String projectName, String projectDescription, String projectStatus, String projectImg, LocalDate startDate){

    }


    @Override
    public void updateProject(Project project) {
        String sql = "UPDATE project SET (project_name = ?, project_desc = ?, project_status = ?, project_img = ?, start_date = ?) AS () WHERE projectID = ?";
        jdbcTemplate.update(sql, project.getProjectName(),project.getProjectDescription(),project.getProjectImg(),project.getStartDate());
    }

    @Override
    public void deleteProject(Project project){
        String sql = "DELETE FROM user_project WHERE project_id = ?";
        jdbcTemplate.update(sql, project.getProjectID());
        sql = "DELETE FROM timesheet WHERE project_id = ?";
        jdbcTemplate.update(sql, project.getProjectID());
        sql = "DELETE FROM project WHERE project_id = ?";
        jdbcTemplate.update(sql, project.getProjectID());
    }

    private Project mapRowToProject(SqlRowSet rowSet) {
        Project result = new Project();
        result.setProjectID(rowSet.getLong("project_id"));
        result.setProjectName(rowSet.getString("project_name"));
        result.setProjectDescription(rowSet.getString("project_desc"));
        result.setProjectStatus(rowSet.getString("project_status"));
        result.setProjectImg(rowSet.getString("project_img"));
        result.setStartDate(rowSet.getDate("start_date").toLocalDate());
        result.setEndDate(rowSet.getDate("end_date").toLocalDate());
        return result;
    }
}
package com.techelevator.dao;

import com.techelevator.model.Project;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProjectDAO implements ProjectDAO{

    private JdbcTemplate jdbcTemplate;

    public JdbcProjectDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createProject(Project newProject){
        String sql = "INSERT INTO project (project_name, project_desc, project_status, project_img, start_date, end_date) VALUES (?,?,?,?,?,?) RETURNING project_id";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, newProject.getProjectName(), newProject.getProjectDescription(), newProject.getProjectStatus(), newProject.getProjectImg(),
                newProject.getStartDate(), newProject.getEndDate());
            if(rowSet.next()){
                newProject.setProjectID(rowSet.getLong("project_id"));
            }
    }

    @Override
    public List<Project> allProjects(){
        List<Project> result = new ArrayList<>();
        String sql = "SELECT project_id, project_name, project_desc, project_status, project_img, start_date, end_date FROM projects";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while(rowSet.next()){
            Project project = mapRowToProject(rowSet);
            result.add(project);
        }
        return result;
    }

    @Override
    public void updateProject(Project project) {
        String sql = "UPDATE project SET (project_name = ?, project_desc = ?, project_status = ?, project_img = ?, start_date = ?) WHERE project_id = ?";
        jdbcTemplate.update(sql, project.getProjectName(),project.getProjectDescription(),project.getProjectImg(),project.getStartDate(), project.getProjectID());
    }

    @Override
    public void deleteProjectById(Long projectID){
        String sql = "DELETE FROM user_project WHERE project_id = ?";
        jdbcTemplate.update(sql, projectID);
        sql = "DELETE FROM timesheet WHERE project_id = ?";
        jdbcTemplate.update(sql, projectID);
        sql = "DELETE FROM project WHERE project_id = ?";
        jdbcTemplate.update(sql, projectID);
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
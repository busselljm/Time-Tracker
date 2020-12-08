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
        String sql = "INSERT INTO project (project_name, project_desc, project_img, end_date) VALUES (?,?,?,?) RETURNING project_id";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, newProject.getProjectName(), newProject.getProjectDescription(), newProject.getProjectImg(),
                 newProject.getEndDate());
            if(rowSet.next()){
                newProject.setProjectID(rowSet.getLong("project_id"));
            }
    }

    @Override
    public List<Project> allProjects(String username){
        List<Project> result = new ArrayList<>();
        String sql = "SELECT  projects.project_id, projects.project_name, projects.project_desc, projects.project_img, projects.end_date\n" +
                "FROM projects\n" +
                "JOIN user_project ON user_project.project_id = projects.project_id\n" +
                "JOIN users ON user_project.user_id = users.user_id\n" +
                "WHERE username = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);

        while(rowSet.next()){
            Project project = mapRowToProject(rowSet);
            result.add(project);
        }
        return result;
    }

    @Override
    public void updateProject(Project project, Long id) {
        String sql = "UPDATE projects SET project_name = ?, project_desc = ?, project_img = ?, end_date = ? WHERE project_id = ?;";
        jdbcTemplate.update(sql, project.getProjectName(), project.getProjectDescription(), project.getProjectImg(),project.getEndDate(), id);
    }

    @Override
    public void deleteProjectById(Long projectID){
        String sql = "DELETE FROM user_project WHERE project_id = ?;";
        jdbcTemplate.update(sql, projectID);
        sql = "DELETE FROM timesheet WHERE project_id = ?;";
        jdbcTemplate.update(sql, projectID);
        sql = "DELETE FROM projects WHERE project_id = ?;";
        jdbcTemplate.update(sql, projectID);
    }

    private Project mapRowToProject(SqlRowSet rowSet) {
        Project result = new Project();
        result.setProjectID(rowSet.getLong("project_id"));
        result.setProjectName(rowSet.getString("project_name"));
        result.setProjectDescription(rowSet.getString("project_desc"));
        result.setProjectImg(rowSet.getString("project_img"));
        result.setEndDate(rowSet.getDate("end_date").toLocalDate());
        return result;
    }


}
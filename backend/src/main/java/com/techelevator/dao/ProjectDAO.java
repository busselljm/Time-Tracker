package com.techelevator.dao;

import com.techelevator.model.Project;

import java.time.LocalDate;
import java.util.List;

public interface ProjectDAO {

    Long createProject(Project newProject);

    void createProjectUser(Long projectID, Long userID);

    List<Project>allProjects(String username);

    Project getProjectByID(Long id);

   void updateProject(Project project, Long id);

   void deleteProjectById(Long projectID);

}

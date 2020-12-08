package com.techelevator.dao;

import com.techelevator.model.Project;

import java.time.LocalDate;
import java.util.List;

public interface ProjectDAO {

    void createProject(Project newProject);

    List<Project>allProjects();

   void updateProject(Project project);

   void deleteProjectById(Long projectID);

}

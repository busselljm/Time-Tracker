package com.techelevator.dao;

import com.techelevator.model.Project;

import java.time.LocalDate;

public interface ProjectDAO {

    Project createProject(long projectID, String projectName, String projectDescription, String projectStatus, String projectImg, LocalDate startDate);

   void updateProject(Project project);

   void deleteProject(Project project);

}

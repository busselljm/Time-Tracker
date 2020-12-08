package com.techelevator.model;

import javax.validation.Valid;
import java.time.LocalDate;

public class Project {

    private Long projectID;
    @Valid
    private String projectName;
    private String projectDescription;
    private String projectImg;
    private LocalDate endDate;

    public Project() {}

    public Project(String projectName, String projectDescription, String projectImg) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectImg = projectImg;
    }

    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(long projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectImg() {
        return projectImg;
    }

    public void setProjectImg(String projectImg) {
        this.projectImg = projectImg;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}

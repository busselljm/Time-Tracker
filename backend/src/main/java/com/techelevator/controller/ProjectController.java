package com.techelevator.controller;

import com.techelevator.dao.ProjectDAO;
import com.techelevator.model.Project;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class ProjectController {
    private final ProjectDAO projectDAO;



    public ProjectController(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    @PutMapping(path = "/projects/update-project/id")
    public void updateProject(@Valid @RequestBody Project updatedProject) {
        projectDAO.updateProject(updatedProject);
    }

    @DeleteMapping(path = "/projects/delete-project/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectDAO.deleteProjectById(id);
    }




    @GetMapping(value = "/projects")
    public List<Project> getProjectsList(Principal principal){
        principal.getName();
        return projectDAO.allProjects(principal.getName());
    }
    @PostMapping("/create-project")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProject(@RequestBody Project project){
        projectDAO.createProject(project);
    }
}

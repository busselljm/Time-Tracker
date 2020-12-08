// @ts-nocheck

import axios from 'axios';

const http = axios.create({baseURL: 'http://localhost:3000'});

export default {

  updateProject(project) {
    return http.put(`/projects/${project.projectID}`, project);
  },

  deleteProject(projectID) {
    return http.delete(`/projects/${projectID}`);
  },

  createProject(project){
    return http.post('/projects', project);
  },

  getAllProjects() {
    return http.get('/projects');
  },

  getProjectByID(projectID) {
    return http.get(`/projects/${projectID}`);
  }

}
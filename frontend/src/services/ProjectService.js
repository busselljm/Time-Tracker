// @ts-nocheck

import axios from 'axios';

const http = axios.create({baseURL: 'http://localhost:3000'});

export default {

  updateProject(project) {
    return http.put(`/projects/${project.id}`, project);
  },

  deleteProject(projectID) {
    return http.delete(`/projects/${projectID}`);
  }

}
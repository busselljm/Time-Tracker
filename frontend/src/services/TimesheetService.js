// @ts-nocheck

import http from 'axios';



export default {

//   updateProject(project) {
//     return http.put(`/projects/${project.projectID}`, project);
//   },

//   deleteProject(projectID) {
//     return http.delete(`/projects/${projectID}`);
//   },

  createTimesheet(timesheet){
    return http.post('/times', timesheet);
  },

  getAllTimesheets() {
    return http.get('/times');
  },

  getTimesheetByID(timeID) {
    return http.get(`/times/${timeID}`);
  }

}
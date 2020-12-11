// @ts-nocheck

import http from 'axios';



export default {


  deleteTimesheet(timeID) {
    return http.delete(`/times/${timeID}`);
  },

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
import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    projects: [],
    activeProject: {
      projectID: 0,
      projectName: '',
      projectDescription: '',
      projectImg: '',
      endDate: ''
    },
    timesheets: [],
       timesheet:  {
            timeID: '',
            projectID: '',
            projectName: '',
            userID: '',
            description: '',
            beginningTime: '',
            endingTime: '',
        },
    token: currentToken || '',
    user: currentUser || {},
    activeUser: {
      id: "",
      firstName: "",
      lastName: "",
      email: "",
      avatar: "",
      managerFirstName: "",
      managerLastName: "",
    },

  },

  mutations: {
    SET_PROJECTS(state, data) {
      state.projects = data;
    },
    SET_TIMESHEETS(state, data) {
      state.timesheets = data;
    },
    SORT_PROJECTS_BY_NAME(state){
      state.projects.sort((project1, project2) => {
        if(project1.projectName < project2.projectName) {
          return -1;
        } else if(project1.projectName > project2.projectName){
          return 1;
        } else {
          return 0;
        }
      });
    },
    SORT_PROJECTS_BY_DATE(state){
      state.projects.sort((project1, project2) => {
        if(project1.endDate < project2.endDate) {
          return -1;
        } else if(project1.endDate > project2.endDate){
          return 1;
        } else {
          return 0;
        }
      });
    },
    SET_ACTIVE_PROJECT(state, data) {
      state.activeProject = data;
    },

    SET_ACTIVE_USER(state, data) {
      state.activeUser = data;
    },
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
      state.projects = [];
    },
  }
})

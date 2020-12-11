<template>
  <div class="timesheet-list">
    <table class="table table-striped">
      <thead>
        <tr>
          <th scope="col">Project Name</th>
          <th scope="col">Description</th>
          <th scope="col">Beginning Time</th>
          <th scope="col">Ending Time</th>
          <th scope="col">Edit</th>
          <th scope="col">Delete</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="timesheet in timesheets" v-bind:key="timesheet.id">
          <td >
           <span>{{timesheet.projectName}}</span>
          </td>
          <td >
           <span>{{timesheet.description}}</span>
          </td>
          <td >
           <span>{{timesheet.beginningTime}}</span>
          </td>
          <td >
           <span>{{timesheet.endingTime}}</span>
          </td>
          <td>
            <!-- <router-link :to="{ name: 'editProject', params: { id: project.projectID } }"
              ><font-awesome-icon icon="edit"/></router-link
            > -->
          </td>
          <td>
            <!-- <a href="#" v-on:click="deleteProject(project.projectID)"><font-awesome-icon icon="trash"/></a> -->
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
import timesheetService from "@/services/TimesheetService.js";
export default {
  name: "time-list",
  data() {
    return {
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
    }
  },
  methods: {
    getTimesheet() {
      timesheetService.getAllTimesheets().then((response) => {
        this.timesheets = response.data;
        console.log(this.timesheets);
        this.$store.commit("SET_TIMESHEETS", response.data);
      });
    },
    sortByName(){
      this.projects.sort();
    },
//  deleteProject(projectID) {
//             projectService.deleteProject(projectID).then(response => {
//               if(confirm('Are you sure you want to delete this card? This action cannot be undone.')){
//                 if (response.status === 200) {
//                   this.getProjects();
//                 }
//             }}).catch(error => {
//                 if (error.response) {
//                     return 'Failed to update project. Response was: ' + error.response.data.message;
//                 } else if (error.request) {
//                     return 'Failed to connect to server.'
//                 } else {
//                     return 'Something went really wrong.'
//                 }
//             }) 
//         },
  },
  created() {
    this.getTimesheet();
  },
};
    //adding to push
</script>


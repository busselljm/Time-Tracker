<template>
  <div class="project-list">
    <table>
      <thead>
        <tr>
          <th>Project</th>
          <th>Edit</th>
          <th>Delete</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="project in projects" v-bind:key="project.id">
          <td width="80%">
           <span>{{project.projectName}} {{project.projectDescription}}</span>
          </td>
          <td>
            <router-link :to="{ name: 'editProject', params: { id: project.projectID } }"
              >Edit</router-link
            >
          </td>
          <td>
            <a href="#" v-on:click="deleteProject(project.projectID)">Delete</a>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
import projectService from "@/services/ProjectService.js";
export default {
  name: "project-list",
  data() {
    return {
      projects: [],
       project:  {
            projectName: "",
            projectDescription: "",
            projectImg: "",
            endDate: ""
        },
    }
  },
  methods: {
    getProjects() {
      projectService.getAllProjects().then((response) => {
        this.projects = response.data;
      });
    },
 deleteProject(projectID) {
            projectService.deleteProject(projectID).then(response => {
                if (response.status === 200) {
                  this.getProjects();
                }
            }).catch(error => {
                if (error.response) {
                    return 'Failed to update project. Response was: ' + error.response.data.message;
                } else if (error.request) {
                    return 'Failed to connect to server.'
                } else {
                    return 'Something went really wrong.'
                }
            }) 
        },
  },
  created() {
    this.getProjects();
  },
};
</script>






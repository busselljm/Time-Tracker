<template>
  <div class="project-list">
    <table class="table table-striped">
      <thead>
        <tr>
          <th scope="col">Image</th>
          <th scope="col">Project</th>
          <th scope="col">Description</th>
          <th scope="col">Edit</th>
          <th scope="col">Delete</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="project in projects" v-bind:key="project.id">
            <td>
           <span><font-awesome-icon icon="dumpster-fire"/></span>
          </td>
          <td >
           <span>{{project.projectName}}</span>
          </td>
          <td >
           <span>{{project.projectDescription}}</span>
          </td>
          <td>
            <router-link :to="{ name: 'editProject', params: { id: project.projectID } }"
              ><font-awesome-icon icon="edit"/></router-link
            >
          </td>
          <td>
            <a href="#" v-on:click="deleteProject(project.projectID)"><font-awesome-icon icon="trash"/></a>
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
        console.log(this.projects);
        this.$store.commit("SET_PROJECTS", response.data);
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
    //adding to push
</script>






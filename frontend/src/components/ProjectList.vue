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
           <h1>{{project.projectName}}</h1>
           <p>{{project.projectDescription}}</p>
          </td>
          <td>
            <router-link :to="{ name: 'EditProject', params: { id: project.id } }"
              >Edit{{project.name}}</router-link
            >
          </td>
          <td>
            <a href="#" v-on:click="deleteProject(project.id)">Delete</a>
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
      });
    },
    deleteProject(id) {
      projectService.deleteProject(id).then((response) => {
        if (response.status === 200) {
          this.getProjects();
        }
      });
    },
  },
  created() {
    this.getProjects();
  },
};
</script>






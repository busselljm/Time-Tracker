<template>
  <form v-on:submit="submitForm" class="projectForm">
    <div class="form-group">
      <label for="name">name:</label>
      <input
        id="name"
        type="text"
        class="form-control"
        v-model="project.projectName"
        autocomplete="off"
      />
    </div>
    <div class="form-group">
      <label for="description">Description:</label>
      <textarea
        id="description"
        class="form-control"
        v-model="project.projectDescription"
      ></textarea>
    </div>

    <div class="form-group">
      <label for="end-date">Completion Date:</label>
      <input
        id="end-date"
        type="date"
        class="form-control"
        v-model="project.endDate"
      />
    </div>
    <div class="form-group">
   <button type="submit"></button>
    </div>
  </form>
</template>

<script>
import projectService from '../services/ProjectService';
export default {
  name: "project-form",
  data() {
    return {
      project: {
        projectName: "",
        projectDescription: "",
        projectImg: "",
        ongoing: false,
        endDate: null,
      },
    };
  },
  methods: {
    submitForm(){
      projectService.createProject(this.project).then(response => {
       if(response.status === 201){
          alert('New project was created successfully');
        }
      }).catch(error => {
        {
          if(error.response){
            this.errorMsg = 'Failed to create project. Response was: ' + error.response.data;
          } else if (error.request){
            this.errorMsg = 'Server did not respond. Could not create project.';
          } else{
            this.errorMsg = 'Something went wrong. Could not send request.';
          }
      }
      });
    },
    
  }
  
  
};
</script>

<style>
</style>
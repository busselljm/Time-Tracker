<template>
  <form v-on:submit.prevent="submitForm" class="projectForm">
    <div class="form-group">
      <label for="name">name:</label>
      <input
        id="name"
        type="text"
        class="form-control"
        v-model="project.name"
        autocomplete="off"
      />
    </div>
    <div class="form-group">
      <label for="description">Description:</label>
      <textarea
        id="description"
        class="form-control"
        v-model="project.description"
      ></textarea>
    </div>

    <div class="form-group">
      <label for="end-date">Completion Date:</label>
      <input
        id="end-date"
        type="date"
        class="form-control"
        v-model="project.completionDate"
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
        name: "",
        description: "",
        image: "",
        ongoing: false,

        completionDate: null,
      },
    };
  },
  methods: {
    submitForm(){
      projectService.createProject(this.project).then(response => {
              console.log(response)

       if(response.status === 201){
        
         
          alert('New project was created successfully');
        }
      }).catch(error => {
        {
          if(error.response){
            this.errorMsg = 'Failed to create board. Response was: ' + error.response.data;
          } else if (error.request){
            this.errorMsg = 'Server did not respond. Could not create board.';
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
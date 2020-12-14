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
      <label for="image">Image (Optional):</label>
      <input
        id="image"
        type="text"
        class="form-control"
        v-model="project.projectImg"
        autocomplete="off"
      />
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
      <button class="btn btn-outline-primary btn-md" type="submit">
        Submit
      </button>
    </div>
  </form>
</template>

<script>
import projectService from "../services/ProjectService";
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
    getValidationErrorMessage() {
      if (this.project.projectName === "") {
        return "Please create a name for your project.";
      } else if (this.project.projectDescription === "") {
        return "Please provide a description of your project.";
      }
    },
    submitForm() {
      let errorMessage = this.getValidationErrorMessage();
      if (errorMessage != null) {
        return alert(errorMessage);
      } else {
        projectService
          .createProject(this.project)
          .then((response) => {
            if (response.status === 201) {
              alert("New project was created successfully");
            }
          })
          .catch((error) => {
            {
              if (error.response) {
                this.errorMsg =
                  "Failed to create project. Response was: " +
                  error.response.data;
              } else if (error.request) {
                this.errorMsg =
                  "Server did not respond. Could not create project.";
              } else {
                this.errorMsg = "Something went wrong. Could not send request.";
              }
            }
          });
      }
    },
  },
};
</script>

<style>

img {
  position: relative;
  width: 50px;
  height: 50px;
  overflow: hidden;
  border-radius: 50%;
}
</style>
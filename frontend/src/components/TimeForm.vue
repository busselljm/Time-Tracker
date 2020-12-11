<template>
  <form v-on:submit="submitForm" class="timeForm">
    <div class="form-group">
      <label for="project name">Project Name:</label>
      <select name="projects" id="project name" v-model="timesheet.projectID">
        <option
          v-bind:value="project.projectID"
          v-for="project in this.$store.state.projects"
          v-bind:key="project.projectName"
        >
          {{ project.projectName }}
        </option>
      </select>
    </div>
    <div class="form-group">
      <label for="description">Description:</label>
      <input
        type="text"
        maxlength="50"
        id="description"
        class="form-control"
        v-model="timesheet.description"
      />
    </div>
    <div class="form-group">
      <label for="beginning time">Beginning Date/Time:</label>
      <input
        id="beginning time"
        class="form-control"
        type="datetime-local"
        v-model="timesheet.beginningTime"
      />
    </div>
    <div class="form-group">
      <label for="description">Ending Date/Time:</label>
      <input
        id="ending time"
        class="form-control"
        type="datetime-local"
        v-model="timesheet.endingTime"
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
import timesheetService from "../services/TimesheetService";
export default {
  name: "time-form",
  data() {
    return {
      timesheet: {
        timeID: "",
        projectID: "",
        userID: "",
        description: "",
        beginningTime: "",
        endingTime: "",
      },
    };
  },
  methods: {
    getValidationErrorMessage() {
      if (this.timesheet.projectID === "") {
        return "Please select a project.";
      } else if (this.timesheet.description === "") {
        return "Please provide a description of your activity.";
      } else if (this.timesheet.beginningTime > this.timesheet.endingTime) {
        return "Beginning date/time must occur before the end.";
      }
    },
    submitForm() {
      let errorMessage = this.getValidationErrorMessage();
      if (errorMessage != null) {
        return alert(errorMessage);
      } else {
      timesheetService
        .createTimesheet(this.timesheet)
        .then((response) => {
          if (response.status === 201) {
            alert("New timesheet was created successfully");
          }
        })
        .catch((error) => {
          {
            if (error.response) {
              this.errorMsg =
                "Failed to create timesheet. Response was: " +
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
</style>
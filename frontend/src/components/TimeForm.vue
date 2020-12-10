<template>
  <form v-on:submit="submitForm" class="timeForm">
    <div class="form-group">
      <label for="project ID">projectId:</label>
      <input
        id="project ID"
        type="text"
        class="form-control"
        v-model="timesheet.projectID"
        autocomplete="off"
      />
    </div>
    <div class="form-group">
      <label for="user id">user id:</label>
      <textarea
        id="userid"
        class="form-control"
        v-model="timesheet.userID"
      ></textarea>
    </div>
    <div class="form-group">
      <label for="description">Description:</label>
      <textarea
        id="description"
        class="form-control"
        v-model="timesheet.description"
      ></textarea>
    </div>
      <div class="form-group">
      <label for="beginning time">beginning time:</label>
      <input 
        id="beginning time"
        class="form-control"
        type="datetime-local"
        v-model="timesheet.beginningTime"
      />
    </div>
      <div class="form-group">
      <label for="description">ending time:</label>
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
    submitForm() {
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
    },
  },
};
</script>

<style>
</style>
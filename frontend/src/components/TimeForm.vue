<template>
  <form class="timeForm">
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
    <div v-if="!runReports" class="form-group">
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
      <button
        v-on:click.prevent="submitForm()"
        class="btn btn-outline-primary btn-md"
        v-if="!runReports"
      >
        Create Log
      </button>
      <button
        v-on:click.prevent="generateReport()"
        class="btn btn-outline-primary btn-md"
        v-if="runReports"
      >
        Run Report
      </button>
    </div>
  </form>
</template>

<script>
import timesheetService from "../services/TimesheetService";
export default {
  name: "time-form",
  props: ["runReports"],
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
      runReport: true,
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
    generateReport() {
      let report = this.$store.state.timesheets;
      if(this.timesheet.projectID != null && this.timesheet.projectID != "") {
        report = report.filter(elem => elem.projectID == this.timesheet.projectID);
      }
      if(this.timesheet.beginningTime != null && this.timesheet.beginningTime != "") {
        report = report.filter(elem => elem.beginningTime >= this.timesheet.beginningTime);
      }
      if (this.timesheet.endingTime != null && this.timesheet.endingTime != "") {
        report = report.filter(elem => elem.endingTime <= this.timesheet.endingTime);
      }
      this.$store.commit(
        "GENERATE_REPORT",
        report
      );
    },
  },
};
</script>

<style>
</style>
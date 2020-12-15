<template>
  <div class="timesheet-list">
    <table class="styled-table">
      <thead>
        <tr>
          <th scope="col">Project Name</th>
          <th scope="col">Description</th>
          <th scope="col">Beginning Time</th>
          <th scope="col">Ending Time</th>
          <th scope="col">Delete</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="timesheet in $store.state.timesheets" v-bind:key="timesheet.id">
          <td>
            <span>{{ timesheet.projectName }}</span>
          </td>
          <td>
            <span >{{ timesheet.description }}</span>
          </td>
          <td>
            <span>{{ timesheet.beginningTime }}</span>
          </td>
          <td>
            <span>{{ Date(timesheet.endingTime) }}</span>
          </td>
          <td>
            <a href="#" v-on:click="deleteTimesheet(timesheet.timeID)"><font-awesome-icon icon="trash" id="trash"/></a>
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
      timesheet: {
        timeID: "",
        projectID: "",
        projectName: "",
        userID: "",
        description: "",
        beginningTime: "",
        endingTime: "",
      },
    };
  },
  methods: {
    getTimesheets() {
      timesheetService.getAllTimesheets().then((response) => {
        this.$store.commit("SET_TIMESHEETS", response.data);
      });
    },
    deleteTimesheet(timeID) {
      timesheetService
        .deleteTimesheet(timeID)
        .then((response) => {
          if (
            confirm(
              "Are you sure you want to delete this timesheet? This action cannot be undone."
            )
          ) {
            if (response.status === 200) {
              this.getTimesheets();
            }
          }
        })
        .catch((error) => {
          if (error.response) {
            return (
              "Failed to update timesheet. Response was: " +
              error.response.data.message
            );
          } else if (error.request) {
            return "Failed to connect to server.";
          } else {
            return "Something went really wrong.";
          }
        });
    },
  },
  created() {
    this.getTimesheets();
  },
};
</script>

<style scoped>
.styled-table {
  border-collapse: collapse;
  font-family: Helvetica, Arial, sans-serif;
  font-size: 1em;
  margin: 25px 0;
  min-width: 400px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
  width: 100%;
  
}

.styled-table thead tr {
  background-color: #07617d;
  color: #ffffff;
  text-align: left;
}

.styled-table th,
.styled-table td {
  padding: 10px 12px;
    position: center;
}

.styled-table tbody tr {
  border-bottom: 1px solid #34495e;
  
}

.styled-table tbody tr:nth-of-type(even) {
  background-color: #f8f6f6;
}

.styled-table tbody tr:last-of-type {
  border-bottom: 5px solid #f9a828;
}


#trash {
  color: rgb(207, 25, 25);
}

</style>
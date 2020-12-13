<template>
  <div class="timesheet-list">
    <table class="table table-striped">
      <b-thead>
        <tr>
          <th scope="col">Project Name</th>
          <th scope="col">Beginning Time</th>
          <th scope="col">Ending Time</th>
          <th scope="col">Time</th>
        </tr>
      </b-thead>
      <b-tbody>
        <tr v-for="timesheet in timesheets" v-bind:key="timesheet.id">
          <td>
            <span>{{ timesheet.projectName }}</span>
          </td>

          <td>
            <span>{{ timesheet.beginningTime }}</span>
          </td>
          <td>
            <span>{{ timesheet.endingTime }}</span>
          </td>
          <td>
            <span>{{
              secondsToHms(
                getDifferenceInTimes(
                  timesheet.beginningTime,
                  timesheet.endingTime
                )
              )
            }}</span>
          </td>
        </tr>
      </b-tbody>
      <b-tfoot>
        <tr>
          <th scope="col"></th>
          <th scope="col"></th>
          <th scope="col"></th>
          <th scope="col">{{ reduce(this.timesheets) }}</th>
        </tr>
      </b-tfoot>
    </table>
  </div>
</template>
<script>
import timesheetService from "@/services/TimesheetService.js";
export default {
  name: "time-list",
  data() {
    return {
      timesheets: [],
      timesheet: {
        timeID: "",
        projectID: "",
        projectName: "",
        userID: "",
        description: "",
        beginningTime: "",
        endingTime: ""
      },
    };
  },
  computed: {
  },
  methods: {
    getDifferenceInTimes(beg, end) {
      if(end == null){
        return 0;
      }
      let dateBeg = new Date(beg);
      let dateEnd = new Date(end);
      this.$store.commit(
        "SET_TOTAL_TIME",
        (dateEnd.getTime() - dateBeg.getTime()) / 1000
      );
      return (dateEnd.getTime() - dateBeg.getTime()) / 1000;
    },
    secondsToHms(d) {
      d = Number(d);
      var h = Math.floor(d / 3600);
      var m = Math.floor((d % 3600) / 60);
      var s = Math.floor((d % 3600) % 60);

      var hDisplay = h > 0 ? h + (h == 1 ? " hour " : " hours ") : "";
      var mDisplay = m > 0 ? m + (m == 1 ? " minute " : " minutes ") : "";
      var sDisplay = s > 0 ? s + (s == 1 ? " second" : " seconds") : "";
      return hDisplay + mDisplay + sDisplay;
    },
    getTimesheet() {
      timesheetService.getAllTimesheets().then((response) => {
        this.timesheets = response.data;
        this.$store.commit("SET_TIMESHEETS", response.data);
      });
    },
    sortByName() {
      this.projects.sort();
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
              this.getTimesheet();
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
    reduce() {
      let total = this.timesheets.reduce((a, b) => {
        return a + this.getDifferenceInTimes(b.beginningTime, b.endingTime);
      }, 0);
      return this.secondsToHms(total);
    },
  },
  created() {
    this.getTimesheet();
  },
};
</script>


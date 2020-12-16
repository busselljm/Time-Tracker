<template>
  <div class="timesheet-list" v-if="$store.state.report.length > 0">
    <table class="styled-table">
      <thead>
        <tr>
          <th scope="col">Project Name</th>
          <th scope="col">Beginning Time</th>
          <th scope="col">Ending Time</th>
          <th scope="col">Time</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="timesheet in $store.state.report" v-bind:key="timesheet.id">
          <td>
            <span>{{ timesheet.projectName }}</span>
          </td>

          <td>
            <span>{{ (new Date(timesheet.beginningTime)).toDateString() }}</span>
          </td>
          <td>
            <span>{{(new Date(timesheet.endingTime)).toLocaleTimeString()}}</span>
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
      </tbody>
      <tfoot>
        <tr>
          <th scope="col"></th>
          <th scope="col"></th>
          <th scope="col"></th>
          <th scope="col">{{ reduce() }}</th>
        </tr>
      </tfoot>
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
  computed: {},
  methods: {
    getDifferenceInTimes(beg, end) {
      if (end == null) {
        return 0;
      }
      let dateBeg = new Date(beg);
      let dateEnd = new Date(end);
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
    sortByName() {
      this.projects.sort();
    },
    reduce() {
      let total = this.$store.state.timesheets.reduce((a, b) => {
        return a + this.getDifferenceInTimes(b.beginningTime, b.endingTime);
      }, 0);
      return this.secondsToHms(total);
    },
  },
  created() {
    timesheetService.getAllTimesheets().then((response) => {
      this.$store.commit("SET_TIMESHEETS", response.data);
    });
  },
};
</script>

<style>

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
</style>


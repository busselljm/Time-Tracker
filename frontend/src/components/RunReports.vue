<template>
  <div class="timesheet-list" v-if="$store.state.report.length > 0">
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
        <tr v-for="timesheet in $store.state.report" v-bind:key="timesheet.id">
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
          <th scope="col">{{ reduce() }}</th>
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


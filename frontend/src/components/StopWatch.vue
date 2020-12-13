<template>
  <div
    id="app"
    style="position: fixed; top: calc(100vh - 80px); left: calc(100vw - 380px)"
    v-if="!loadingTimesheet"
  >
    <!-- ACTIVE TIME LOG DOESN'T EXIST -->
    <div v-if="$store.state.timesheet == null">
      <select
        name="projects"
        id="project name"
        v-model="selectedProject"
        v-if="$store.state.timesheet == null"
      >
        <option
          v-bind:value="project.projectID"
          v-for="project in this.$store.state.projects"
          v-bind:key="project.projectName"
        >
          {{ project.projectName }}
        </option>
      </select>

      <button :disabled="selectedProject == null" v-on:click="start">Start Time Log</button>
    </div>

    <!-- ACTIVE TIME LOG EXISTS -->
    <div v-if="$store.state.timesheet != null">
      <button v-on:click="stop" v-if="$store.state.timesheet != null">
        Complete Log
      </button>
      <p>{{ hours }}:{{ minutes }}:{{ seconds }}</p>
    </div>
  </div>
</template>

<script>
import timesheetService from "@/services/TimesheetService.js";
import ProjectService from "../services/ProjectService";

export default {
  name: "App",
  data() {
    return {
      elapsedTime: 0,
      timer: undefined,
      selectedProject: null,
      loadingTimesheet: false,
    };
  },
  computed: {
    formattedElapsedTime() {
      const date = new Date(null);
      date.setSeconds(this.elapsedTime / 1000);
      const utc = date.toUTCString();
      return utc.substr(utc.indexOf(":") - 2, 8);
    },
    seconds() {
      return Math.floor((this.elapsedTime / 1000) % 60);
    },
    minutes() {
      return Math.floor((this.elapsedTime / 1000 / 60) % 60);
    },
    hours() {
      return Math.floor((this.elapsedTime / (1000 * 60 * 60)) % 24);
    },
    days() {
      return Math.floor(this.elapsedTime / (1000 * 60 * 60 * 24));
    },
  },
  filters: {
    formatTime(value) {
      if (value < 10) {
        return "0" + value;
      }
      return value;
    },
  },
  methods: {
    start() {
      timesheetService.createActiveTimesheet(this.selectedProject).then(
        () => {
          this.refreshActive()
        },
        (error) => {
          console.log(error);
          alert("Couldn't create new time log");
        }
      );
    },
    stop() {
      timesheetService.completeActiveTimesheet("I did it!").then(
        () => {
          clearInterval(this.timer);
          this.$store.commit("SET_ACTIVE_TIMESHEET", null);
        },
        (error) => {
          console.log(error);
          alert("Couldn't complete time log");
        }
      );
    },
    reset() {
      this.elapsedTime = 0;
    },
    refreshActive() {
      this.loadingTimesheet = true;
      timesheetService.getActiveTimesheet().then(
        (response) => {
          this.loadingTimesheet = false;
          if (response.data != null && response.data != "") {
            this.$store.commit("SET_ACTIVE_TIMESHEET", response.data);
            let startTime = Date.parse(response.data.beginningTime);
            let currentTime = new Date().getTime();
            
            this.elapsedTime = currentTime - startTime;

            this.timer = setInterval(() => {
              this.elapsedTime += 1000;
            }, 1000);
          } else {
            this.$store.commit("SET_ACTIVE_TIMESHEET", null);
          }
        },
        (error) => {
          this.loadingTimesheet = false;
          // TODO: handle better
          console.log(error);
          alert("Couldn't read active time log data");
        }
      );
    },
  },
  created() {
    ProjectService.getAllProjects().then((response) => {
      this.$store.commit("SET_PROJECTS", response.data);
    });
    this.refreshActive();
  },
};
</script>

     
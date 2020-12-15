<template>
  <div
    class="footer-container"
    v-if="!loadingTimesheet"
  >
    <!-- ACTIVE TIME LOG DOESN'T EXIST -->
    <div class="footer-row" v-if="$store.state.timesheet == null">
      <select
        name="projects"
        class="footer-row-elem"
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

      <button
        class="footer-row-elem btn btn-primary"
        :disabled="selectedProject == null"
        v-on:click="start"
      >
        Start Time Log
      </button>
    </div>

    <!-- ACTIVE TIME LOG EXISTS -->
    <div class="footer-row" v-if="$store.state.timesheet != null">
      <span class="footer-row-elem">Project: {{$store.state.timesheet.projectName}}</span>
      <input class="footer-row-elem" maxlength="50" type="text" v-model="description" placeholder="Describe work performed"/>
      <button
        class="footer-row-elem btn btn-danger"
        v-on:click="stop"
        v-if="$store.state.timesheet != null"
      >
        Complete Log
      </button>
      <span class="footer-timer">{{ formattedElapsedTime }}</span>
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
      description: "",
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
      this.description = "";
      timesheetService.createActiveTimesheet(this.selectedProject).then(
        () => {
          this.refreshActive();
        },
        (error) => {
          console.error(error);
        }
      );
    },
    stop() {
      if (this.description == null || this.description == "") {
        alert("Description required to log record.");
        return;
      }
      timesheetService.completeActiveTimesheet(this.description).then(
        () => {
          clearInterval(this.timer);
          this.$store.commit("SET_ACTIVE_TIMESHEET", null);
          this.refreshProjects();
        },
        (error) => {
          console.error(error);
        }
      );
    },
    refreshProjects() {
      this.projectService.getAllProjects().then((response) => {
        this.$store.commit("SET_PROJECTS", response.data);
      });
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
          // TODO: handle better
          console.error(error);
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

<style scoped>
#app {
  margin: 0;
  border: 0 none;
  padding: 10px;
  z-index: 999999;
  position: fixed;
  bottom: 0;
  right: 0;
  width: 100vw;
  height: 46.5px;
}
.footer-row {
  float: right;
}
.footer-row-elem {
  margin-left: 15px;
}
.footer-timer {
  left: 15px;
  position: fixed;
  bottom: 10px;
}
</style>

     
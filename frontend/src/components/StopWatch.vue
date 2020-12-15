<template>
  <div
    class="card"
    v-if="!loadingTimesheet"
  >
  <h3 class="mb-3 header">Timer</h3>
    <!-- ACTIVE TIME LOG DOESN'T EXIST -->
    <div v-if="$store.state.timesheet == null">
      <p>Choose project:</p>
      <select
        class="mb-3 form-control"
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
      <div>
      <button
        class="btn btn-success btn-lg btn-block"
        :disabled="selectedProject == null"
        v-on:click="start"
      >
        Start Time Log
      </button>
      </div>
    </div>

    <!-- ACTIVE TIME LOG EXISTS -->
    <div class="footer-row" v-if="$store.state.timesheet != null">
      <label>Project: {{$store.state.timesheet.projectName}}</label>
      <div>
      <input class="form-control mb-3" maxlength="50" type="text" v-model="description" placeholder="Describe work"/>
      </div>
      <p>{{ formattedElapsedTime }}</p>
      <div>
      <button
        class="btn btn-danger btn-lg btn-block"
        v-on:click="stop"
        v-if="$store.state.timesheet != null"
      >
        Complete Log
      </button>
      </div>
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
          this.getTimesheets();
        },
        (error) => {
          console.error(error);
        }
      );
    },
    getTimesheets() {
      timesheetService.getAllTimesheets().then((response) => {
        this.$store.commit("SET_TIMESHEETS", response.data);
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

h3 {
  margin: 0;
  background-color:  #363636;
  text-align: center;
  padding: .5rem;
  color: #d6d6d6;
  }

.card {
  position: fixed;
  top: 115px;
  padding: .5rem;
  left: 2rem;
}

p {
  text-align: center;
  font-weight: bold;
  font-size: 1.5rem;
}

label {
  padding: 0;
}

.btn-success {
  background-color: #a3e000; 
  color: #1a2400;
  font-weight: bold;
  border: none;
}

.btn-success:hover {
    background-color: #c1f531;
    transition: all .25s ease;
     color: #1a2400;
  }

  .btn-danger {
    background-color: #c74513;
  }

  .btn-danger:hover {
    background-color: rgb(252, 116, 62);
    border: none;
  }

</style>

     
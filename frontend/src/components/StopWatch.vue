<template>
  <div id="app">
    <button v-on:click="start">Start</button>
    <button v-on:click="stop">Stop</button>
    <button v-on:click="reset">Reset</button>
    <p>{{ hours }}:{{ minutes }}:{{ seconds }}</p>
  </div>
</template>

<script>
// import timesheetService from "@/services/TimesheetService.js";
export default {
  name: "App",
  data() {
    return {
      elapsedTime: 0,
      timer: undefined,
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
    }
  },
  filters: {
    formatTime(value) {
      if (value < 10) {
        return "0" + value;
      }
      return value;
    }
  },
    methods: {
    start() {
      this.timer = setInterval(() => {
        this.elapsedTime += 1000;
      }, 1000);
    },
    stop() {
        console.log(this.elapsedTime);
       clearInterval(this.timer);
    },
    reset() {
      this.elapsedTime = 0;
    }
  }
  
}
</script>

     
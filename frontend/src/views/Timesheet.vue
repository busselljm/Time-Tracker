<template>
  <div>
    <button v-on:click="runReports = false">Create Time</button>
    <button v-on:click="runReports = true">Run Reports</button>
    <time-form v-bind:runReports="runReports" v-if="runReports != undefined"/>
    <time-list v-if="!runReports"/>
    <run-reports v-if="runReports === true"/>
  </div>
</template>

<script>
import TimeForm from "../components/TimeForm.vue";
import TimeList from "../components/TimeList.vue";
import projectService from "@/services/ProjectService.js";
import RunReports from "../components/RunReports.vue";

export default {
  components: { TimeList, TimeForm, RunReports },
  data() {
    return{
      runReports: undefined
    }
  },
  methods: {
    getProjects() {
      projectService.getAllProjects().then((response) => {
        this.projects = response.data;
        console.log(this.projects);
        this.$store.commit("SET_PROJECTS", response.data);
      });
    },
  },
  created() {
    this.getProjects();
  },
};
</script>

<style>
</style>
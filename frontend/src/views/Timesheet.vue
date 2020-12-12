<template>
  <div>
    <time-form />
    <time-list />
    <run-reports />
  </div>
</template>

<script>
import TimeForm from "../components/TimeForm.vue";
import TimeList from "../components/TimeList.vue";
import projectService from "@/services/ProjectService.js";
import RunReports from "../components/RunReports.vue";

export default {
  components: { TimeList, TimeForm, RunReports },
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
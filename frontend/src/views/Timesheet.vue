<template>
<div>
  <time-form/>
  <time-list/>

</div>

</template>

<script>
import TimeForm from '../components/TimeForm.vue'
import TimeList from '../components/TimeList.vue'
import projectService from "@/services/ProjectService.js";

export default {
  components: { TimeList, TimeForm },
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
}

</script>

<style>

</style>
<template>
  <form v-on:submit.prevent>
    <div>
        <label for="name">Project Name</label>
        <input type="text" v-model="projectName"/>
    </div>
    <div>
        <label for="description">Project Description</label>
        <input type="text" v-model="projectDescription"/>
    </div>
    <div class="dropdown is-active">
        <label for="status">Project Status</label>
        <select name="status" v-model="projectStatus">
            <option value="proposed">Proposed</option>
            <option value=""></option>
            <option value="ongoing">Ongoing</option>
            <option value="completed">Completed</option>
    </div>
    </form>
</template>

<script>
import projectService from "../services/ProjectService";

export default {
    props: ["projectID"],
    data() {
        return {
            projectName: "",
            projectDescription: "",
            projectStatus: "",
            projectImg: "",
            startDate: "",
            endDate: ""
        };
    },

}
</script>

<style>

</style>
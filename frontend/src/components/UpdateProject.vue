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
    <div>
        <label for="img"></label>
        <input type="text" v-model="projectImg"/>
    </div>
    <div>
        <label for="end-date">Project End Date</label>
        <input type="date" v-model="endDate"/>
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
            projectImg: "",
            endDate: ""
        };
    },
    methods: {
        updateProject() {
            const project = {
                
            }
        }
    }

}
</script>

<style>

</style>
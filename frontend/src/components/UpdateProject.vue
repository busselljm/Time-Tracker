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
        <label for="img">Project Image</label>
        <input type="text" v-model="projectImg"/>
        <font-awesome-icon icon="edit"/>       
        
    </div>
    <div>
        <label for="end-date">Project End Date</label>
        <input type="date" v-model="endDate"/>
    </div>
    <div>
    <button type="submit" v-on:click="updateProject()"><font-awesome-icon icon="check"/></button>
    </div>
    <div>
    <button type="submit" v-on:click="cancelUpdate()"><font-awesome-icon icon="window-close"/></button>
    </div>
    </form>
</template>

<script>
import projectService from "../services/ProjectService";


export default {
    name: "create-project",
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
                projectID: this.$route.params.id,
                projectName: this.projectName,
                projectDescription: this.projectDescription,
                projectImg: this.projectImg,
                endDate: this.endDate
            };

            projectService.updateProject(project).then(response => {
                if (response.status === 200) {
                    this.$router.push('/projects');
                }
            }).catch(error => {
                if (error.response) {
                    return 'Failed to update project. Response was: ' + error.response.data.message;
                } else if (error.request) {
                    return 'Failed to connect to server.'
                } else {
                    return 'Something went really wrong.'
                }
            })
        },
        deleteProject() {
            projectService.deleteProject(this.projectID).then(response => {
                if (response.status === 200) {
                    this.$router.push('/projects');
                }
            }).catch(error => {
                if (error.response) {
                    return 'Failed to update project. Response was: ' + error.response.data.message;
                } else if (error.request) {
                    return 'Failed to connect to server.'
                } else {
                    return 'Something went really wrong.'
                }
            }) 
        },
        cancelUpdate() {
            this.$router.push('/projects');
        }
        
    }

}
</script>

<style>
    
</style>
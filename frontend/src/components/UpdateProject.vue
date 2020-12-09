<template>
  <form v-on:submit.prevent>
    <div>
      <label for="projectName">Project Name</label>
      <input name="projectName" type="text" v-model="projectName" />
    </div>
    <div>
      <label for="projectDescription">Project Description</label>
      <input
        name="projectDescription"
        type="text"
        v-model="projectDescription"
      />
    </div>
    <div>
      <label for="projectImg">Project Image</label>
      <input name="projectImg" type="text" v-model="projectImg" />
    </div>
    <div>
      <label for="endDate">Project End Date</label>
      <input name="endDate" type="date" v-model="endDate" />
    </div>
    <div>
      <button type="submit" v-on:click="updateProject()">
        <font-awesome-icon icon="check" />
      </button>
    </div>
    <div>
      <button type="submit" v-on:click="cancelUpdate()">
        <font-awesome-icon icon="window-close" />
      </button>
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
    },
   created() {
        projectService.getProjectByID(this.projectID).then(response => {
            this.$store.commit("SET_ACTIVE_PROJECT", response.data);
            this.projectName = response.data.projectName;
            this.projectDescription = response.data.projectDescription;
            this.projectImg = response.data.projectImg;
            this.endDate = response.data.endDate;
        }).catch(error => {
        if (error.response.status == 404) {
          this.$router.push("/not-found");
        }
      });

}
}
//adding to push
</script>

<style>
</style>
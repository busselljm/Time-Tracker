<template>
  <div class="project-list">
    <table class="styled-table">
      <b-thead>
        <tr>
          <th scope="col" class="sort" v-on:click="$store.commit('SORT_PROJECTS_BY_RECENT')">
            Sort Recent
          </th>
          <th scope="col" class="sort" v-on:click="$store.commit('SORT_PROJECTS_BY_NAME')">
            Project Name
          </th>
          <th scope="col" class="sort" v-on:click="$store.commit('SORT_PROJECTS_BY_DATE')">
            End Date
          </th>
          <th>Edit</th>
          <th>Delete</th>
        </tr>
      </b-thead>
      <b-tbody>
        <tr v-for="project in $store.state.projects" v-bind:key="project.id">
          <td>
            <span>
              <img v-if="project.projectImg != ''" :src="project.projectImg" />
              <img
                v-if="project.projectImg === ''"
                src="http://placegoat.com/200/200"
              />
            </span>
          </td>
          <td>
            <span>{{ project.projectName }}</span>
          </td>
          <td>
            <span>{{ project.endDate }}</span>
          </td>
          <td>
            <router-link
              :to="{ name: 'editProject', params: { id: project.projectID } }"
              ><font-awesome-icon icon="edit" id="edit"
            /></router-link>
          </td>
          <td>
            <a href="#" v-on:click="deleteProject(project.projectID)"
              ><font-awesome-icon icon="trash" id="trash"
            /></a>
          </td>
          <td v-if="isManager === true">
            <input type="checkbox" v-model="project.shared" />
          </td>
        </tr>
      </b-tbody>
    </table>
  </div>
</template>
<script>
import projectService from "@/services/ProjectService.js";
export default {
  name: "project-list",
  data() {
    return {
      project: {
        projectName: "",
        projectDescription: "",
        projectImg: "",
        endDate: "",
        shared: false,
      },
      isManager: { ...this.$store.state.isManager },
    };
  },
  methods: {
    getProjects() {
      projectService.getAllProjects().then((response) => {
        this.$store.commit("SET_PROJECTS", response.data);
      });
    },
    deleteProject(projectID) {
      if (
        confirm(
          "Are you sure you want to delete project card? This action cannot be undone."
        )
      ) {
        projectService
          .deleteProject(projectID)
          .then((response) => {
            {
              if (response.status === 200) {
                this.getProjects();
              }
            }
          })
          .catch((error) => {
            if (error.response) {
              return (
                "Failed to update project. Response was: " +
                error.response.data.message
              );
            } else if (error.request) {
              return "Failed to connect to server.";
            } else {
              return "Something went really wrong.";
            }
          });
      }
    },
  },
  created() {
    this.getProjects();
  },
};
</script>

<style scoped>
.styled-table {
  border-collapse: collapse;
  font-family: Helvetica, Arial, sans-serif;
  font-size: 1em;
  margin: 25px 0;
  min-width: 400px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
  width: 100%;
}

.styled-table thead tr {
  background-color: #07617d;
  color: #ffffff;
  text-align: left;
}

.styled-table th,
.styled-table td {
  padding: 10px 12px;
  position: center;
}

.styled-table tbody tr {
  border-bottom: 1px solid #34495e;
}

.styled-table tbody tr:nth-of-type(even) {
  background-color: #f8f6f6;
}

.styled-table tbody tr:last-of-type {
  border-bottom: 5px solid #f9a828;
}

#edit {
  color: #3498db;
}

#trash {
  color: rgb(207, 25, 25);
}

.sort {
  cursor: pointer;
}
</style>






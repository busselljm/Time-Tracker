<template>
  <div>
    <button v-on:click="createProject = !createProject" class="btn btn-primary">Create Project</button>
    <project-form v-if="createProject"/>
    <project-list/>
    
  </div>
</template>

<script>
import ProjectForm from "../components/ProjectForm.vue";
import ProjectList from '../components/ProjectList.vue';
export default {
    components: {
        ProjectForm,
        ProjectList
    },
    data () {
      return {
        createProject: false
      }
    }
};
</script>

<style>

</style>
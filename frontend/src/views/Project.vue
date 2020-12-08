<template>
  <div>
    <project-form />
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
    }
};
</script>

<style>

</style>
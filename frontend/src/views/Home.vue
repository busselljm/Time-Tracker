<template>
  <div class="home">


    <h1>Welcome </h1>


    <button class="btn btn-outline-primary btn-md"><router-link to="/projects">My Projects</router-link></button>
    <button class="btn btn-outline-primary btn-md"><router-link to="/profile">My Profile</router-link></button>
  <stop-watch/>
  
  </div>
</template>

<script>
import StopWatch from '../components/StopWatch.vue';

export default {
      components: {
        StopWatch
    }
   
};

</script>

<style>
</style>

<template>
  <h1>Logout</h1>
</template>

<script>
export default {
  created() {
    this.$store.commit('LOGOUT');
    this.$store.commit('SET_PROJECTS', []);
    this.$router.push('/login');
  }
};
</script>

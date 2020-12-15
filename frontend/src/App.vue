<template>
  <div id="app">
    <navigation-bar v-if="!isLogin" :items="navigationItems" />
    <div class="container window-container" :class="isLogin ? 'center-vertically center-horizontally' : ''">
      <router-view />

      <stop-watch v-if="!isLogin && $store.state.user.id != null" />

    </div>
    <footer>
      <p class="copyright text-center">
        Copyright &copy; {{ year }}. All Rights Reserved.
      </p>
    </footer>
  </div>
</template>

<script>
import NavigationBar from "@/components/Navigation.vue";
import StopWatch from "@/components/StopWatch";

export default {
  components: {
    NavigationBar,
    StopWatch,
  },
  data() {
    return {
      navigationItems: [
        { text: "Home", component: "/" },
        { text: "About Us", component: "about-us"},
        { text: "Profile", component: "profile" },
        { text: "Projects", component: "projects" },
        { text: "Time Logs", component: "times" }
      ],
    };
  },
  computed: {
    isLogin() {
      return this.$route.name === "login";
    },
    year() {
      return new Date().getFullYear();
    },
  },
};
</script>

<style scoped>
.window-container {
  margin-top: 115px;
}

footer {
  background-color: #363636;
  padding: 1rem;
}

footer p {
  margin: 0;
}

.copyright {
  color: #686868;
}



</style>

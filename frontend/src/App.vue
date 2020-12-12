<template>
  <div id="app">
    <navigation-bar :items="navigationItems" />
    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<script>
import NavigationBar from '@/components/Navigation.vue'

export default {
  components: {
    NavigationBar
  },
  data() {
    return {
      navigationItems: [
        { text: 'Home', component: 'home' },
        { text: 'Profile', component: 'profile' },
        { text: 'Projects', component: 'projects' },
        { text: 'Logout', component: 'logout' }
      ]
    }
  }
}
</script>
<template>
  <div id="app">
    <navigation-bar :items="navigationItems" />
    <div class="container">
      <router-view />
      <stop-watch/>
    </div>
  </div>

</template>

<script>
import NavigationBar from '@/components/Navigation.vue'
import StopWatch from './components/StopWatch'

export default {
  components: {
    NavigationBar,
    StopWatch
  },
  data() {
    return {
      navigationItems: [
        { text: 'Home', component: '/' },
        { text: 'Profile', component: 'profile' },
        { text: 'Projects', component: 'projects' },
        { text: 'Time Logs', component: 'times' },
        { text: 'Logout', component: 'logout' }
      ]
    }
  }
}
</script>

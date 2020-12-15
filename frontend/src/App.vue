<template>
  <div id="app">
    <navigation-bar :items="navigationItems" />
    <body>    
    <div class="container window-container">
      <router-view />
      <stop-watch/>
    </div>
    </body>
    <div class="page-footer"></div>
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
        { text: 'Time Logs', component: 'times' }
        // { text: 'Logout', component: 'logout' }
      ]
    }
  }
}
</script>

<style>

.window-container>div{
  padding-bottom: 46.5px;
}
.page-footer {
  position: fixed;
  bottom:0;
  right:0;
  width: 100vw;
  height: 46.5px;
  background-color:#3498db;
}
</style>

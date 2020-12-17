<template>
<div>
<nav>
    <div class="overlay" @mouseenter="showDropdown = false" />
    <div class="container">
      <div class="row">
        <div class="col-md-3">
          <router-link :to="{ name: 'splash' }">
            <img class="left" src="kronos-logo.svg" alt="Kronos Logo" />
          </router-link>
        </div>

        <div class="col-md-9 center-vertically justify-content-end">
          <ul class="list-unstyled text-right" v-if="$store.state.token !== ''">
            <li v-for="item in items" :key="item.component" class="d-inline" @mouseenter="showDropdown = false">
              <router-link :to="item.component">{{ item.text }}</router-link>
            </li>
            <li class="d-inline" @mouseenter="showDropdown = true">
              <img :src="$store.state.user.avatar" :alt="`${$store.state.user.firstName}'s profile picture for KronoTrackr`">
            </li>
          </ul>
        </div>
      </div>
      <ul v-if="showDropdown" class="dropdown list-unstyled" @mouseleave="showDropdown = false">
        <li class="h5">Hello, {{ $store.state.user.firstName }}</li>
        <li class="mb-2"><router-link :to="{ name: 'profile' }">Profile</router-link></li>
        <li>
              <router-link
                :to="{ name: 'login' }"
                class="btn btn-primary btn-md btn-block"
                >LOGOUT</router-link
              >
            </li>
      </ul>
    </div>
  </nav>
</div>

</template>

<script>
export default {
  props: {
    items: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      showDropdown: false
    }
  }
};
</script>

<style scoped>
nav {
  background-color: #363636;
  padding: 1rem;
  position: fixed;
  width: 100%;
  top: 0;
  z-index: 99999;
}

.container {
  position: relative;
  z-index: 9999
}

.overlay {
  background: #363636;
  height: 96px;
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 0;
}

nav ul {
  margin-bottom: 0;
}

nav ul li {
  padding: 0 1rem;
}

nav li a:not(.btn) {
  color: #666;
  transition: all 0.25s ease;
}

nav li a:not(.btn):hover {
  /* every anchor tag (link) that does NOT have class of .btn */
  color: #a3e000;
  transition: all 0.25s ease;
  text-decoration: none;
}

nav li:last-child a {
  color: #fff;
}

.left {
  height: 4rem;
  display: flex;
  align-self: left;
  flex-wrap: wrap;
}

li img {
  width: 50px;
  border-radius: 50%;
  margin-right: 1rem;
}

.dropdown {
  position: absolute;
  background: #363636;
  top: 4rem;
  z-index: 99999;
  display: block;
  right: -3.5rem;
  padding: 1rem .5rem 2rem .5rem;
  color: #fff;
  width: 250px;
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
}

.dropdown ul {
  padding: 0;
  margin: 0;
}
</style>
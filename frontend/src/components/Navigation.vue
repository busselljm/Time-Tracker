<template>
  <nav>
    <div class="container">
      <ul class="list-unstyled text-right" v-if="$store.state.token !== ''">
        <li v-for="(item) in items" :key="item.component" class="d-inline">
            <router-link :to="item.component">{{ item.text}}</router-link>
        </li>
        <li class="d-inline">
          <router-link :to="{name: 'logout' }" class="btn btn-primary btn-md">LOGOUT</router-link>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
export default {
    props: {
        items: {
            type: Array,
            required: true
        }
    },
}
</script>

<style scoped>
  nav {
    background-color: #363636;
    padding: 1.5rem;
    position: fixed;
    width:100%;
    top: 0;
    z-index: 99999;
  }

  nav ul {
    margin-bottom: 0;
  }

  nav ul li {
    padding: 0 1rem;
  }

  nav li a:not(.btn) {
     color: #666;
     transition: all .25s ease;
  }

  nav li a:not(.btn):hover { /* every anchor tag (link) that does NOT have class of .btn */
     color: #a3e000;
     transition: all .25s ease;
     text-decoration: none;

  }
</style>
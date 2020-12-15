<template>
  <nav>
    <div class="container">
      <ul class="list-unstyled text-right" v-if="$store.state.token !== ''">
        <li v-for="(item) in items" :key="item.component" class="d-inline">
            <router-link :to="item.component">{{ item.text}}</router-link>
        </li>
        <li class="d-inline">
        <router-link :to="{name: 'logout' }" class="btn btn-md">LOGOUT</router-link>
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

  nav li a {
     color: #666;
     transition: all .25s ease;
  }

  nav li a:hover {
     color: #a3e000;
     transition: all .25s ease;
     text-decoration: none;

  }

  nav li a.btn {
    background-color: #a3e000;
    font-weight: bold;
    color: #1a2400
  }

  nav li a.btn:hover {
    background-color: #c1f531;
     color: #1a2400;

  }



</style>
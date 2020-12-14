<template>
  <nav>
    <div class="container">
      <ul class="list-unstyled text-right">
        <li v-for="(item, i) in items" :key="item.component" class="d-inline">
            <router-link :to="item.component" :class="i === items.length - 1 ? 'btn btn-primary btn-sm' : ''">{{ item.text}}</router-link>
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
    background-color: #3498db;
    padding: .5rem;
    margin-bottom: 1rem;
  }

  nav ul {
    margin-bottom: 0;
  }

  nav ul li {
    padding: 0 1rem;
  }

  nav li a {
    color: #fff;
  }
</style>
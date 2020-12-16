<template>
  <div>
    <h1 class="header">About KronoTrakr</h1>
    <p>About the app</p>
    <h2 class="header">Meet the Team</h2>

    <div class="row mt-4">
      <div
        v-for="member in teamMembers"
        :key="member.linkedIn"
        class="col-md-6 mb-4"
      >
        <single-card>
          <img :src="member.image" :alt="`Picture of ${member.name}`" />
          <h3 class="name h4">
            {{ member.name }}
            <a v-if="member.linkedIn" :href="`https://www.linkedin.com/in/${member.linkedIn}/`"><i class="fa fa-linkedin-square"></i></a>
          </h3>
          <p>{{ member.description }}</p>
        </single-card>
      </div>
    </div>
  </div>
</template>

<script>
import SingleCard from '@/components/SingleCard.vue'

export default {
  components: {
    SingleCard
  },
  props: {
    teamMembers: {
      type: Array,
      required: true,
    },
  },
};
</script>

<style scoped>
.card {
  height: 100%;
}

ul li {
  list-style-type: none;
}

.header {
  background-color: #9c468f;
  display: block;
  text-align: center;
  color: #fefefe;
  padding: 1rem;
}

.name {
  font-weight: bold;
}

img {
  width: 150px !important;
  height: auto;
  border-radius: 50%;
  margin-bottom: 1.5rem;
}
</style>
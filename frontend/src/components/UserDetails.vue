<template>
<div>
  <h1>My Profile</h1>
  <img v-bind:src="user.avatar" alt="">
  <div class= "personal-information">
  <h2>User Information</h2>
  <p>{{user.firstName}}</p>
  <p>{{user.lastName}}</p>
  <p>{{user.email}}</p>
  <p>{{user.manager}}</p>
  </div>
  <button> <router-link :to="{ name: 'editProfile'}"
              ><font-awesome-icon icon="edit"/></router-link
            ></button>
  </div>
</template>

<script>
import userService from "../services/UserServices.js";
export default {
  data() {
    return {
      user: {
        username: "",
        firstName: "",
        lastName: "",
        avatar: "",
        manager: "",
        email: "",
      },
    };
  },
  methods: {
    getUser() {
      userService.getUser().then((response) => {
        this.user.username = response.data.username;
        this.user.firstName = response.data.firstName;
        this.user.lastName = response.data.lastName;
        this.user.avatar = response.data.avatar;
        this.user.manager = response.data.manager;
        this.user.email = response.data.email;
      });
    },
  },
  created() {
    this.getUser();
  },
};
</script>

<style>
</style>
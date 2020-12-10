<template>
  <h1>{{ this.user.firstName }}</h1>
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
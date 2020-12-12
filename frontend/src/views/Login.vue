<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <div class="input-group flex-nowrap">
        <span class="input-group-text" id="addon-wrapping"><font-awesome-icon icon="user-secret"/> </span>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          aria-label="Username"
          aria-describedby="addon-wrapping"
          v-model="user.username"
        required
        autofocus
        />
      </div>
      <div class="input-group flex-nowrap">
        <span class="input-group-text" id="addon-wrapping"><font-awesome-icon icon="lock"/> </span>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          aria-label="Password"
          aria-describedby="addon-wrapping"
          v-model="user.password"
        required
        />
      </div>
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
      <div class="button">
      <button class="btn btn-primary btn-md" type="submit">Sign in</button>
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style>
  .input-group, .flex-nowrap {
    margin: auto;
    padding: 0px 30vw 10px 30vw;
  }
  
  .button{
    display: flex;
    justify-content: center;
    padding: 10px 0px;
  }

</style>

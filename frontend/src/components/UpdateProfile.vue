<template>
  <form v-on:submit.prevent>
    <h1>Update Profile</h1>
    <div>
      <label for="firstName"> First Name:</label>
      <input name="firstName" type="text" v-model="loggedInUser.firstName" />
    </div>
    <div>
      <label for="lastName">Last Name:</label>
      <input name="lastName" type="text" v-model="loggedInUser.lastName" />
    </div>
    <div>
      <label for="avatar">Profile Picture:</label>
      <input name="avatar" type="text" v-model="loggedInUser.avatar" />
    </div>
    <div>
      <label for="email">Email:</label>
      <input name="email" type="email" v-model="loggedInUser.email" />
    </div>
    <div>
      <select v-model="selectedManagerUserId">
        <option v-for="user in allOtherUsers" :key="user.id" :value="user.id">
          {{ user.firstName }} {{ user.lastName }}
        </option>
      </select>        
    </div>
    <div>
      <button type="submit" v-on:click="updateProfile()">
        <font-awesome-icon icon="check" />
      </button>

      <button type="submit" v-on:click="cancelUpdate()">
        <font-awesome-icon icon="window-close" />
      </button>
    </div>
  </form>
</template>

<script>
import UserServices from "../services/UserServices.js";
import { handleServiceError } from '@/js/utility.methods.js';

export default {
  data() {
    return {
      users: [],
      loggedInUser: this.$store.state.user, // Logged in user data already stored in state. Assigning to local variable to modify
      selectedManagerUserId: undefined,
    };
  },
  methods: {
    updateProfile() {
      console.log('hey')
      // Don't need to make a copy of it. User is already stored in local component data.
      // this.user is automatically binded when user types in form

      this.loggedInUser.managerID = this.selectedManagerUserId // Assigning manager to user by unique ID
      this.loggedInUser.role = 'user'

      UserServices.updateProfile(this.loggedInUser)
      .then((response) => {
        if (response.status === 200) {
          this.$router.push("/profile");
        }
      })
      .catch((error) => { handleServiceError(error) });
    },
    cancelUpdate() {
      this.$router.push("/profile");
    },
  },
  computed: {
    allOtherUsers() {
      // Get all users EXCEPT logged in user
      return this.users.filter((user) => {
        return user.id != this.$store.state.user.id // single '=' bc comparing to string
      })
    },
  },
  mounted() {
    // Get all users on component
    UserServices.getAllUsers().then((response) => {
      this.users = response.data;
    });
  },
};
</script>
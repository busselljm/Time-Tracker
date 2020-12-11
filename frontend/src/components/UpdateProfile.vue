<template>
  <form v-on:submit.prevent>
    <div>
      <label for="firstName"> First Name</label>
      <input name="firstName" type="text" v-model="firstName" />
    </div>
    <div>
      <label for="lastName">Last Name</label>
      <input
        name="lastName"
        type="text"
        v-model="lastName"
      />
    </div>
    <div>
      <label for="avatar">Profile Picture</label>
      <input name="avatar" type="text" v-model="avatar" />
    </div>
    <div>
      <label for="email">email</label>
      <input name="email" type="email" v-model="email" />
    </div>
    <div class="dropdown">
      <button class= "btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" 
      data-toggle ="dropdown" aria-haspopup="true" aria-expanded="false">Select Manager</button>
      <div v-for="user in users" v-bind:key="user.username">
          <input name="manager" type="text" v-model="managerFullName" />
      </div>
    
    </div>
    <div>
    <div>
      <button type="submit" v-on:click="updateProfile()">
        <font-awesome-icon icon="check" />
      </button>
    </div>
    
      <button type="submit" v-on:click="cancelUpdate()">
        <font-awesome-icon icon="window-close" />
      </button>
    </div>
  </form>
</template>

<script>
import UserServices from '../services/UserServices.js';

export default {
  data() {
    return {
      users: [],
      user: {
        id: "",
        firstName: "",
        lastName: "",
        email: "",
        avatar: "",
        managerFirstName: "",
        managerLastName: "",
      },
    };
  },

  methods: {
    updateProfile() {
      const user = {
        id: this.$route.params.id,
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        avatar: this.avatar,
        managerFirstName: this.managerFirstName,
        managerLastName: this.managerLastName,
      };

      UserServices.updateProfile(user)
        .then((response) => {
          if (response.status === 200) {
            this.$router.push("/profile");
          }
        })
        .catch((error) => {
          if (error.response) {
            return (
              "Failed to update profile. Response was: " +
              error.response.data.message
            );
          } else if (error.request) {
            return "Failed to connect to server.";
          } else {
            return "Something went really wrong.";
          }
        });
    },

    getAllUsers() {
      UserServices.getAllUsers(this.user.id).then((response) => {
        this.users = response.data;
      });
    },

    cancelUpdate() {
      this.$router.push("/profile");
    },
  },

  created() {
      UserServices.getUser().then(response => {
            this.$store.commit("SET_ACTIVE_USER", response.data);
            this.firstName = response.data.firstName;
            this.lastName = response.data.lastName;
            this.email = response.data.email;
            this.avatar = response.data.avatar;
            this.managerFirstName = response.data.managerFirstName;
            this.managerLastName = response.data.managerLastName;
            
        }).catch(error => {
        if (error.response.status == 404) {
          this.$router.push("/not-found");
        }
      });
    this.getAllUsers();
  },

  computed:{
      managerFullName: {
          get() {
              return `${this.managerFirstName} ${this.managerLastName}`;
          }, set(newValue) {
              const m= newValue.match(/(\S*)\s+(.*)/);
              this.managerFirstName= m[1];
              this.managerLastName= m[2];
          }

      }
  }
};
</script>

<style>
</style>
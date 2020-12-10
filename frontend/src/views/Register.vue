<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="input-group flex-nowrap">
        <label for="firstName" class="sr-only">First Name</label>
        <span class="input-group-text" id="addon-wrapping"
          ><font-awesome-icon icon="id-card" />
        </span>
        <input
          type="text"
          id="firstName"
          class="form-control"
          placeholder="First Name"
          v-model="user.firstName"
          required
          autofocus
        />
      </div>
      <div class="input-group flex-nowrap">
        <label for="lastName" class="sr-only">Last Name</label>
        <span class="input-group-text" id="addon-wrapping"
          ><font-awesome-icon icon="id-card" />
        </span>
        <input
          type="text"
          id="lastName"
          class="form-control"
          placeholder="Last Name"
          v-model="user.lastName"
          required
          autofocus
        />
      </div>
      <div class="input-group flex-nowrap">
        <label for="email" class="sr-only">Email Address</label>
        <span class="input-group-text" id="addon-wrapping"
          ><font-awesome-icon icon="at" />
        </span>
        <input
          type="text"
          id="email"
          class="form-control"
          placeholder="Email Address"
          v-model="user.email"
          required
          autofocus
        />
      </div>
      <div class="input-group flex-nowrap">
        <label for="avatar" class="sr-only">Avatar</label>
        <span class="input-group-text" id="addon-wrapping"
          ><font-awesome-icon icon="portrait" />
        </span>
        <input
          type="text"
          id="avatar"
          class="form-control"
          placeholder="Image url"
          v-model="user.avatar"
          required
          autofocus
        />
      </div>
      <div class="input-group flex-nowrap">
        <label for="username" class="sr-only">Username</label>
        <span class="input-group-text" id="addon-wrapping"
          ><font-awesome-icon icon="user-secret" />
        </span>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
      </div>
      <div class="input-group flex-nowrap">
        <label for="password" class="sr-only">Password</label>
        <span class="input-group-text" id="addon-wrapping"
          ><font-awesome-icon icon="lock" />
        </span>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
      </div>
      <div class="input-group flex-nowrap">
        <span class="input-group-text" id="addon-wrapping"
          ><font-awesome-icon icon="lock" />
        </span>
        <input
          type="password"
          id="confirmPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />
      </div>
      <!-- <div class="form-row">
        <div class="col-md-4 mb-3">
          <label for="validationServer01">First name</label>
          <input
            type="text"
            class="form-control is-valid"
            id="validationServer01"
            placeholder="First name"
            value="Mark"
            required
          />
          <div class="valid-feedback">Looks good!</div>
        </div>
        <div class="col-md-4 mb-3">
          <label for="validationServer02">Last name</label>
          <input
            type="text"
            class="form-control is-valid"
            id="validationServer02"
            placeholder="Last name"
            value="Otto"
            required
          />
          <div class="valid-feedback">Looks good!</div>
        </div>
        </div> -->
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
      <div>
        <button class="btn btn-lg btn-primary" type="submit">
          Create Account
        </button>
      </div>
    </form>
    <!-- <form>
      <div class="form-row">
        <div class="col-md-4 mb-3">
          <label for="validationServer01">First name</label>
          <input
            type="text"
            class="form-control is-valid"
            id="validationServer01"
            placeholder="First name"
            value="Mark"
            required
          />
          <div class="valid-feedback">Looks good!</div>
        </div>
        <div class="col-md-4 mb-3">
          <label for="validationServer02">Last name</label>
          <input
            type="text"
            class="form-control is-valid"
            id="validationServer02"
            placeholder="Last name"
            value="Otto"
            required
          />
          <div class="valid-feedback">Looks good!</div>
        </div>
        <div>
          <label for="validationServerUsername">Username</label>
          <div class="input-group">
            <div class="input-group-prepend">
              <span class="input-group-text" id="inputGroupPrepend3">@</span>
            </div>
            <label for="username" class="sr-only">Username</label>
            <input
              type="text"
              id="validationServerUsername"
              class="form-control is-invalid"
              placeholder="Username"
              aria-describedby="inputGroupPrepend3"
              v-model="user.username"
              required
              autofocus
            />
            <input
              type="text"
              class="form-control is-invalid"
              id="validationServerUsername"
              placeholder="Username"
              aria-describedby="inputGroupPrepend3"
              required
            />
            <div class="invalid-feedback">Please choose a username.</div>
          </div>
        </div>
      </div>
      <div class="form-row">
        <div class="col-md-6 mb-3">
          <label for="validationServer03">City</label>
          <input
            type="text"
            class="form-control is-invalid"
            id="validationServer03"
            placeholder="City"
            required
          />
          <div class="invalid-feedback">Please provide a valid city.</div>
        </div>
        <div class="col-md-3 mb-3">
          <label for="validationServer04">State</label>
          <input
            type="text"
            class="form-control is-invalid"
            id="validationServer04"
            placeholder="State"
            required
          />
          <div class="invalid-feedback">Please provide a valid state.</div>
        </div>
        <div class="col-md-3 mb-3">
          <label for="validationServer05">Zip</label>
          <input
            type="text"
            class="form-control is-invalid"
            id="validationServer05"
            placeholder="Zip"
            required
          />
          <div class="invalid-feedback">Please provide a valid zip.</div>
        </div>
      </div>
      <div class="form-group">
        <div class="form-check">
          <input
            class="form-check-input is-invalid"
            type="checkbox"
            value=""
            id="invalidCheck3"
            required
          />
          <label class="form-check-label" for="invalidCheck3">
            Agree to terms and conditions
          </label>
          <div class="invalid-feedback">You must agree before submitting.</div>
        </div>
      </div>
      <button class="btn btn-primary" type="submit">Submit form</button>
    </form> -->
  </div>
</template>

<script>
//firstName
//lastName
//email
//avatar
//manager
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style>
label {
  display: flex;
  padding: 20px;
}

.button {
  display: flex;
  justify-content: center;
  padding: 10px 0px;
}
.input-group,
.flex-nowrap {
  margin: auto;
  padding: 0px 30vw 10px 30vw;
}
</style>


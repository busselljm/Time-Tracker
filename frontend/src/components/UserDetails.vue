<template>
  <div>
    <h1>My Profile</h1>
    <img v-bind:src="$store.state.user.avatar" :alt="`Profile Photo of ${$store.state.user.firstName} ${$store.state.user.lastName}`" />
    <div class="personal-information">
      <h2>User Information</h2>
      <p>{{ $store.state.user.firstName }}</p>
      <p>{{ $store.state.user.lastName }}</p>
      <p>{{ $store.state.user.email }}</p>
      <p>{{ $store.state.user.managerFirstName }} {{ $store.state.user.managerLastName }}</p>
    </div>
    <button>
      <router-link :to="{ name: 'editProfile' }">
        <font-awesome-icon icon="edit"/>
      </router-link>
    </button>
  </div>
</template>
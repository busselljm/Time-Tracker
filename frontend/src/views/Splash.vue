<template>
  <div class="no-boot">
    <br/>
    <div class="full-container">
      <img
        id="mockup"
        src="img\LaptopImage.png"
        alt="Computer Mockup w/ Product"
      />
      <div class="container">
        <h3>Project Management Software Trusted Worldwide</h3>
        <p>
          Quickly log hours to a project by creating your own time logs or by
          using our live time tracking tool. Management can share projects and
          run time reports. Never miss a project deadline again with
          KronoTrakr™.
        </p>
        <router-link to="register" class="btn btn-primary" tag="button"
          >Register an Account</router-link
        >
        <br />
        <router-link to="login" class="btn btn-primary" tag="button"
          >Login</router-link
        >
      </div>
    </div>
    <br/>
    <div class="review"><h2>"I love this software. By switching to KronoTrakr, our time reporting has never been more accurate."</h2><h6>- Bob Ross</h6></div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
#mockup {
  width: 700px;
  height: auto;
}
.full-container {
  display: flex;
  flex-flow: row nowrap;
  justify-items: space-between;
  padding: 0px;
  margin: 0px;
}

.container {
  display: flex;
  flex-flow: column nowrap;
  justify-items: center;
  margin: 0px;
  padding: 0px;
}
</style>
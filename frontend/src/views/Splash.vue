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
    <br/>

 <blockquote><p class="quotation"> 
  "I love this software. By switching to KronoTrakr, our time reporting has never been more accurate."</p>
<footer>- Bob Ross</footer>
</blockquote> 
  </div>
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

.quotation{
  font-size: 30px;
  margin: 0 auto;
  quotes: "\201C""\201D""\2018""\2019";
  padding: 10px 20px;
  line-height: 1.4;
}

.quotation:before {
  content: open-quote;
  display: inline;
  height: 0;
  line-height: 0;
  left: -10px;
  position: relative;
  top: 30px;
  color: #ccc;
  font-size: 3em;
}
.quotation::after {
  content: close-quote;
  display: inline;
  height: 0;
  line-height: 0;
  left: 10px;
  position: relative;
  top: 35px;
  color: #ccc;
  font-size: 3em;
}

footer{
  margin:0;
  text-align: right;
  font-size: 1.5em;
  font-style: italic;
}
</style>
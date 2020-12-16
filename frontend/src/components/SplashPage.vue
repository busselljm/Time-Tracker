<template>
<div>
  <h1>Welcome to KronoTrakr</h1>
  <img src="https://aboutleaders.com/wp-content/uploads/2018/05/Productivity.jpg" alt="people working"/>
  <div v-if="$store.state.token == '' ">
 <a href='/login'>Login </a>
 <a href='/register'>Register an account </a>
 </div>
  </div>

  
</template>

<script>
export default {

}
</script>

<style scoped>
h1{
  text-align: left;
}

/* img{
margin-bottom: 50px;
} */

div{
  margin-bottom: 30px;
}

a{
  display: block;
}


</style>
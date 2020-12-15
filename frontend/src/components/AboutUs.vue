<template>
  <div>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <div>
      <h1 class="header">About THYMETrackr</h1>
    </div>

    <div>
      <p>About the app</p>
    </div>

    <div>
      <h2 class="header">Meet the Team</h2>
    </div>

    <div>
      <ul>
        <div>
          <img src="../../img/jared.jpg" alt="Picture of Jared" />

          <li class="name">
            Jared Bussell<a href="https://www.linkedin.com/in/jared-bussell/"
              ><i class="fa fa-linkedin-square"></i
            ></a>
          </li>
          <p>This is where a paragraph about you will go</p>
        </div>

        <div>
          <img src="../../img/brittany.jpg" alt="Picture of Brittany" />
          <li class="name">
            Brittany Blakenship<a
              href="https://www.linkedin.com/in/brittanyblankenship/"
              ><i class="fa fa-linkedin-square"></i
            ></a>
          </li>
          <p>This is where a paragraph about you will go</p>
        </div>

        <div>
          <img src="../../img/Ross.png" alt="Picture of Ross" />
          <li class="name">Ross Mather<a
              href="https://www.linkedin.com/in/ross-mather/"
              ><i class="fa fa-linkedin-square"></i
            ></a></li>
          <p>
            Before Tech Elevator I was living and working in Saint-Petersburg,
            Russia. I took an introductory programming course while in Russia
            and was immediately hooked. I quit my job and moved back to America,
            to pursue a career in Tech.
          </p>
        </div>

        <div>
          <img src="../../img/winnie.jpg" alt="Picture of Winnie" />
          <li class="name">Winnie Kimani<a
              href="https://www.linkedin.com/in/winnie-kimani/"
              ><i class="fa fa-linkedin-square"></i
            ></a></li>
          <p>This is where a paragraph about you will go</p>
        </div>

        <div>
          <img src="../../img/Stephanie.png" alt="Picture of Stephanie" />
          <li class="name">Stephanie Berning<a
              href="https://www.linkedin.com/in/stephanieaberning/"
              ><i class="fa fa-linkedin-square"></i
            ></a></li>
          <p>
            Hi! I'm a software developer from Cincinnati, OH. I recently
            completed the Java bootcamp at Tech Elevator. My background includes
            customer service and clerical work at the Hamilton County
            Courthouse. Working at the courthouse helped me gain a wealth of
            experience with critical thinking and data analysis. Most of my
            career was working with lawyers, courtroom staff, and defendants;
            helping them organize their legal troubles. Through the mountains of
            paperwork, I found myself wishin I was around more modern
            technology. During my free time, by video lectures, I tried learning
            how to code on my own; but found I needed something with more
            structure. I hope to continue to improve my coding skills through
            group collaboration adn add value to where I work.
          </p>
        </div>
      </ul>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
ul li {
  list-style-type: none;
}

.header {
  background-color: #9c468f;
  display: block;
  text-align: center;
  color: #fefefe;
  padding: 1rem;
}

.name {
  font-weight: bold;
}

img {
  width: 25%;
  height: auto;
}



</style>
<template>
  <div>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <div>
      <h1 class="header">About KronoTrakr</h1>
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
          <li class="name">
            Ross Mather<a href="https://www.linkedin.com/in/ross-mather/"
              ><i class="fa fa-linkedin-square"></i
            ></a>
          </li>
          <p>
            Before Tech Elevator I was living and working in Saint-Petersburg,
            Russia. I took an introductory programming course while in Russia
            and was immediately hooked. I quit my job and moved back to America,
            to pursue a career in Tech.
          </p>
        </div>

        <div>
          <img src="../../img/winnie.jpg" alt="Picture of Winnie" />
          <li class="name">
            Winnie Kimani<a href="https://www.linkedin.com/in/winnie-kimani/"
              ><i class="fa fa-linkedin-square"></i
            ></a>
          </li>
          <p>
            My passion is to provide quality service led by integrity at all
            time. With the exposure to data analysis and quality improvement
            dealing with performance improvement tools. This is what peaked my
            interest to fully immerse myself in the tech field. My goal is to
            apply the knowledge and skills I have gained to a company that
            fosters growth and provides challenging opportunities.
          </p>
        </div>

        <div>
          <img src="../../img/Stephanie.png" alt="Picture of Stephanie" />
          <li class="name">
            Stephanie Berning<a
              href="https://www.linkedin.com/in/stephanieaberning/"
              ><i class="fa fa-linkedin-square"></i
            ></a>
          </li>
          <p>
            Hi! I'm a software developer from Cincinnati, OH. I recently
            completed the Java bootcamp at Tech Elevator. My background includes
            customer service and clerical work at the Hamilton County
            Courthouse, working with lawyers, courtroom staff, and defendants.
            Working at the courthouse helped me gain a wealth of experience with
            critical thinking and data analysis. I hope to continue
            to improve my coding skills through group collaboration adn add
            value to where I work.
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
<template>
  <header class="header">
    <div class="logo-container">
      <RouterLink to="/">
        <img src="@/assets/img/logo.png" alt="SSAFIT Logo">

      </RouterLink>

    </div>
    <!-- <div class="running-icon">
      <img src="@/assets/naruto2.gif" alt="Running Naruto" ref="running2">
    </div> -->
    <nav class="nav-center-menu">
      <ul>
        <li><RouterLink to="/">운동영상</RouterLink></li>
        <li><RouterLink to="/about">운동백과</RouterLink></li>
        <li><RouterLink to="/services">커뮤니티</RouterLink></li>
      </ul>
    </nav>
    <nav class="nav-right-menu">
      <ul>

        <li><RouterLink :to="{name: 'LoginComponent'}">로그인</RouterLink></li>
      </ul>
    </nav>
  </header>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import LoginComponent from '../user/LoginComponent.vue';

const running2 = ref(null);

onMounted(() => {
  // let direction = 1; // 1 for right, -1 for left
  // function animateSequence() {
  //   if (running2.value) {
  //     const animationName = direction === 1 ? 'run-right' : 'run-left';
  //     running2.value.style.animation = `${animationName} 6s linear infinite alternate`;
  //     direction *= -1; // Reverse the direction for the next run
  //   }
  // }
  // animateSequence();
});
</script>



<style scoped>
* {
  font-weight: 500 !important;
}
.header {
  display: flex;
  position: sticky;
  top:0;
  align-items: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.logo-container {
  /* background-color: hsl(212, 65%, 81%); */
  padding: 10px 20px;
  display: flex; /* Make logo-container a flex container */
  align-items: center; /* Center-align children vertically */
  margin-right: auto; /* Pushes the nav-menu to the right */
}

.logo-container img {
  height: 50px; 
}

/* .running-icon img {
  width: 100px;
  position: absolute;
}

@keyframes run-right {
  from { transform: translateX(0); }
  to { transform: translateX(100%); }
}

@keyframes run-left {
  from { transform: translateX(100%); }
  to { transform: translateX(0); }
} */

.title {
  font-size: 20px; /* Optional: adjust font size as needed */
  margin-left: 10px; /* Space between logo and title */
}

.nav-right-menu {
  flex-grow: 1;
  /* background-color: white; */
  padding: 10px 20px;
  display: flex;
  justify-content: flex-end;
}

.nav-center-menu {
  flex-grow: 1;
  /* background-color: white; */
  padding: 10px 20px;
  display: flex;
  justify-content: flex-end
}

nav ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  display: flex;
}

nav li {
  margin-left: 20px;
}

nav li a {
  color: #3c90e2;  
  text-decoration: none;
  padding: 5px 10px;
  border-radius: 5px;
  transition: background-color 0.3s, color 0.3s;
}

nav li a:hover {
  /* background-color: hsl(210, 25%, 78%); */
  color: white;
}
</style>

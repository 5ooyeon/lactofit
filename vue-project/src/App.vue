<script setup>
import { RouterLink, RouterView } from 'vue-router'
import headerNav from "@/components/common/headerNav.vue";
</script>

<template>
  <headerNav/>
  <div class="container">
    <RouterView></RouterView>
  </div>
</template>

<style>
* {
  margin: 0;
  padding: 0;
  text-decoration: none;
  /* background-color: #1a1a1c; */
  font-family: 'Pretendard';
  font-weight: 400;
  /* color: white */
}
@font-face {
  font-family: 'jua';
  src: url('assets/fonts/BMJUA_ttf.ttf') format('truetype');
}
@font-face {
  font-family: 'GmarketSansLight';
  src: url('assets/fonts/GmarketSansTTFLight.ttf') format('truetype');
}
@font-face {
  font-family: 'Pretendard';
  src: url('assets/fonts/Pretendard-ExtraLight.ttf') format('truetype');
  font-weight: 200;
}
@font-face {
  font-family: 'Pretendard';
  src: url('assets/fonts/Pretendard-Light.ttf') format('truetype');
  font-weight: 300;
}
@font-face {
  font-family: 'Pretendard';
  src: url('assets/fonts/Pretendard-Regular.ttf') format('truetype');
  font-weight: 400;
}
@font-face {
  font-family: 'Pretendard';
  src: url('assets/fonts/Pretendard-SemiBold.ttf') format('truetype');
  font-weight: 500;
}
/* .container {
  display: flex;
  width: 80%;
  justify-content: center;
} */
</style>

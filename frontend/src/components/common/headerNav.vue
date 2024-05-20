<template>
  <header class="navbar navbar-expand-lg rounded">
    <nav class="container">
      <div class="navbar-collapse d-lg-flex" id="navbarsExample11">
        <RouterLink :to="{ name: 'Main' }" class="navbar-brand col-lg-3 me-0">SSAFIT</RouterLink>
        <ul class="navbar-nav col-lg-6 justify-content-lg-center">
          <li class="nav-item">
            <a class="nav-link" href="#routine">운동백과</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#youtube">영상PICK</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#social">#오운완</a>
          </li>
        </ul>
        <div class="d-lg-flex col-lg-3 justify-content-lg-end align-items-center">
          <button class="signin" @click="loginWithGoogle" v-if="!authStore.user">
            <svg class="svg__google" viewBox="0 0 256 262" preserveAspectRatio="xMidYMid" xmlns="http://www.w3.org/2000/svg">
              <path d="M255.878 133.451c0-10.734-.871-18.567-2.756-26.69H130.55v48.448h71.947c-1.45 12.04-9.283 30.172-26.69 42.356l-.244 1.622 38.755 30.023 2.685.268c24.659-22.774 38.875-56.282 38.875-96.027" fill="#4285F4"></path>
              <path d="M130.55 261.1c35.248 0 64.839-11.605 86.453-31.622l-41.196-31.913c-11.024 7.688-25.82 13.055-45.257 13.055-34.523 0-63.824-22.773-74.269-54.25l-1.531.13-40.298 31.187-.527 1.465C35.393 231.798 79.49 261.1 130.55 261.1" fill="#34A853"></path>
              <path d="M56.281 156.37c-2.756-8.123-4.351-16.827-4.351-25.82 0-8.994 1.595-17.697 4.206-25.82l-.073-1.73L15.26 71.312l-1.335.635C5.077 89.644 0 109.517 0 130.55s5.077 40.905 13.925 58.602l42.356-32.782" fill="#FBBC05"></path>
              <path d="M130.55 50.479c24.514 0 41.05 10.589 50.479 19.438l36.844-35.974C195.245 12.91 165.798 0 130.55 0 79.49 0 35.393 29.301 13.925 71.947l42.211 32.783c10.59-31.477 39.891-54.251 74.414-54.251" fill="#EB4335"></path>
            </svg>
            Google 로그인
          </button>
          <div v-if="authStore.user" style="display: flex;">
            <div class="notification">
              <div class="bell-container">
                <div class="bell"></div>
              </div>
            </div>
            <img :src="authStore.user.userProfileImage" ref="profileImage" class="user-profile-img dropdown-toggle" id="profileDropdown" data-bs-toggle="dropdown" aria-expanded="false">
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="profileDropdown">
              <li><button class="dropdown-item" @click="goToProfilePage">프로필</button></li>
              <li><button class="dropdown-item" @click="logout">로그아웃</button></li>
            </ul>
          </div>
        </div>
      </div>
    </nav>
  </header>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useAuthStore } from '../../stores/auth.js'
import { useRoute, useRouter } from 'vue-router'
import * as bootstrap from 'bootstrap'

const authStore = useAuthStore()
const route = useRoute()
const router = useRouter()

const profileImage = ref(null)

const loginWithGoogle = () => {
  authStore.loginWithGoogle()
}

const logout = () => {
  authStore.logout()
}

const goToProfilePage = () => {
  router.push({ name: 'UserProfile' })
}

onMounted(() => {
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  const code = urlParams.get('code');
  if (code) {
    authStore.fetchUserOpenId(code)
  }

  // Bootstrap JavaScript 초기화
  const dropdown = new bootstrap.Dropdown(profileImage.value)

  // 이미지 경로 확인
  console.log('User profile image URL:', authStore.user?.userProfileImage)
})
</script>

<style scoped>
.navbar {
  position: sticky;
  top: 0;
  left: 0;
  width: 100%;
  background-color: white;
  z-index: var(--z-fixed);
  transition: .4s;
}

.navbar-brand, .nav-link {
  color: #333;
}

.navbar-brand:hover, .nav-link:hover {
  color: #000;
}

.signin {
  float: right;
  max-width: 320px;
  display: flex;
  padding: 0.3rem .8rem;
  font-size: 0.875rem;
  line-height: 1.25rem;
  font-weight: 700;
  text-align: center;
  font-family: "Montserrat", sans-serif;
  vertical-align: middle;
  align-items: center;
  border-radius: 0.5rem;
  border: 1px solid #19242b;
  gap: 0.75rem;
  color: #19242b;
  background-color: whitesmoke;
  cursor: pointer;
  transition: all 0.25s cubic-bezier(0, 0.87, 0.12, 1);
}

.signin:hover {
  transform: scale(1.025);
}

.signin:active {
  transform: scale(0.975);
}

.svg__google {
  height: 24px;
  width: auto;
}

.user-profile-img {
  width: 2.5rem;
  border-radius: 50%;
  cursor: pointer;
  margin-left: 1rem; /* 추가된 부분 */
}

.dropdown-menu-end {
  right: 0;
  left: auto;
}

.bell-container {
  position: relative;
}

.bell {
  border: 2.17px solid black;
  border-radius: 10px 10px 0 0;
  width: 15px;
  height: 17px;
  background: transparent;
  display: block;
  position: relative;
  top: -3px;
}
.bell::before,
.bell::after {
  content: "";
  background: black;
  display: block;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  height: 2.17px;
}
.bell::before {
  top: 100%;
  width: 20px;
}
.bell::after {
  top: calc(100% + 4px);
  width: 7px;
}

.notification {
  background: transparent;
  border: none;
  padding: 0; /* 변경된 부분 */
  border-radius: 50px;
  cursor: pointer;
  transition: 300ms;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 1rem; /* 추가된 부분 */
}

.notification::before {
  content: "";
  color: white;
  font-size: 10px;
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background-color: red;
  position: absolute;
  right: -4px; /* 수정된 부분 */
  top: 3px; /* 수정된 부분 */
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.notification:hover {
  background: rgba(170, 170, 170, 0.062);
}

.notification:hover > .bell-container {
  animation: bell-animation 650ms ease-out 0s 1 normal both;
}

@keyframes bell-animation {
  20% {
    transform: rotate(15deg);
  }

  40% {
    transform: rotate(-15deg);
    scale: 1.1;
  }
  60% {
    transform: rotate(10deg);
    scale: 1.1;
  }
  80% {
    transform: rotate(-10deg);
  }
  0%,
  100% {
    transform: rotate(0deg);
  }
}
</style>

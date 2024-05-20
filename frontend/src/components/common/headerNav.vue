<template>
  <header class="navbar navbar-expand-lg">
    <nav class="container">
      <div class="navbar-collapse d-lg-flex" id="navbarsExample11">
        <RouterLink :to="{ name: 'Main' }" class="navbar-brand col-lg-3 me-0">
          <img src="../../assets/img/LACTOFIT_HEADER.png" class="logo-img" />
        </RouterLink>
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
            <div class="notification dropstart me-5" type="button" data-bs-toggle="dropdown" aria-expanded="false" :class="{ 'has-unread-notifications': hasUnreadNotifications }">
              <div class="bell-container">
                <div class="bell"></div>
              </div>
              <ul class="dropdown-menu">
                <div class="notifications-container">
                  <div v-if="notifications.length !== 0">
                    <li v-for="noti in notifications" :key="noti.notificationId">
                      <div class="alert" :class="{ 'alert-read': noti.notificationRead }">
                        <div class="alert-prompt-wrap" @click="openModal(noti)">
                          <p class="text-sm text-yellow-700">
                            {{ noti.notificationContent }}
                            <p class="noti-regDate">{{ noti.notificationRegDate }}</p>
                          </p>
                        </div>
                        <button class="btn" @click="deleteNoti(noti.notificationId)">
                          <svg viewBox="0 0 15 17.5" height="17.5" width="15" xmlns="http://www.w3.org/2000/svg" class="icon">
                            <path transform="translate(-2.5 -1.25)" d="M15,18.75H5A1.251,1.251,0,0,1,3.75,17.5V5H2.5V3.75h15V5H16.25V17.5A1.251,1.251,0,0,1,15,18.75ZM5,5V17.5H15V5Zm7.5,10H11.25V7.5H12.5V15ZM8.75,15H7.5V7.5H8.75V15ZM12.5,2.5h-5V1.25h5V2.5Z" id="Fill"></path>
                          </svg>
                        </button>
                      </div>
                    </li>
                  </div>
                  <div class="no-noti" v-else>
                    알림이 없습니다.
                  </div>
                </div>
              </ul>
            </div>

            <div class="dropdown">
              <img :src="`${authStore.user.userProfileImage}`" class="user-profile-img dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
              <ul class="dropdown-menu">
                <li><RouterLink :to="{ name: 'MyPageView', params: { id: authStore.user.userId } }">나의 프로필</RouterLink></li>
                <li><button @click="logout()">내 정보 수정</button></li>
                <li><button @click="logout()">로그아웃</button></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </nav>
  </header>

  <!-- Modal -->
  <div class="modal fade" id="notificationModal" tabindex="-1" aria-labelledby="notificationModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="notificationModalLabel">{{ selectedNotification?.notificationContent }}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <p>{{ selectedNotification?.notificationContent }}</p>
          <p class="noti-regDate">{{ selectedNotification?.notificationRegDate }}</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" @click="deleteNoti(selectedNotification?.notificationId)">삭제</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useAuthStore } from '../../stores/auth.js'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'
import { Modal } from 'bootstrap'

const authStore = useAuthStore()
const route = useRoute()
const router = useRouter()

const notifications = ref([])
const selectedNotification = ref(null)
let notificationModal = null

const loginWithGoogle = () => {
  authStore.loginWithGoogle()
}

const logout = () => {
  authStore.logout()
}

const goToProfilePage = () => {
  router.push({ name: 'UserProfile' })
}

const hasUnreadNotifications = ref(false)

const getNotifications = () => {
  const userId = authStore.user.userId;
  axios.get('http://localhost:8080/notifications/' + userId)
    .then((Response) => {
      notifications.value = Response.data;
      console.log(notifications.value);
      // Check if there are any unread notifications
      hasUnreadNotifications.value = notifications.value.some(noti => !noti.notificationRead);
    });
}

// 알림 삭제
const deleteNoti = (notificationId) => {
  axios.delete(`http://localhost:8080/notifications/${notificationId}`)
    .then(() => {
      notifications.value = notifications.value.filter(noti => noti.notificationId !== notificationId)
    })
}

// 모달 열기
const openModal = (noti) => {
  selectedNotification.value = noti
  if (!notificationModal) {
    const modalElement = document.getElementById('notificationModal')
    notificationModal = new Modal(modalElement)
  }
  //알림 읽음 처리
  axios.put('http://localhost:8080/notifications/'+noti.notificationId)
  .then((response) => {
    noti.notificationRead = true;
    hasUnreadNotifications.value = notifications.value.some(noti => !noti.notificationRead);
    console.log('read')
  })
  // event.stopPropagation()
  notificationModal.show()
}

onMounted(() => {
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  const code = urlParams.get('code');

  if (authStore.user) {
    getNotifications();
  }
  console.log(notifications.value)
  if (code) {
    authStore.fetchUserOpenId(code).then(() => {
      if (authStore.user) {
        getNotifications();
      }
    });
  }
});
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
.logo {
  display: flex;
  align-items: center; /* 수직 중앙 정렬 */
}

.logo-img {
  height: 100%; /* 부모 요소의 높이에 맞추기 */
  max-height: 60px; /* 최대 높이 설정 */
  width: auto; /* 비율 유지 */
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
  right: -4px;
  top: 3px;
  display: none; /* 기본적으로 숨김 */
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.has-unread-notifications::before {
  display: block; /* 안 읽은 알림이 있을 때만 표시 */
}

/* .notification::before {
  display: none;
} */

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

.notifications-container {
  width: 320px;
  height: auto;
  font-size: 0.875rem;
  line-height: 1.25rem;
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.flex {

}

.flex-shrink-0 {
  flex-shrink: 0;
}

.alert {
  border-color: grey;
  border-radius: 0.375rem;
  padding: 1rem;
  display: flex;
  margin-bottom: 0 !important;
  align-items: center; /* Ensure items are centered vertically */
}

.alert-svg {
  height: 1.25rem;
  width: 1.25rem;
  /* color: rgb(250 204 21); */
}

.alert-prompt-wrap {
  margin-left: 0.75rem;
  flex-grow: 1; /* Allow it to take up the remaining space */
}

.alert-prompt-link {
  font-weight: 500;
  color: rgb(141, 56, 0);
  text-decoration: underline;
}

.alert-prompt-link:hover {
  color: rgb(202 138 4);
}

.alert button {
  margin-left: auto; /* Push the button to the right */
}

.alert-read {
  background-color: grey;
}

</style>

<template>
    <div class="profile-page container">
      <div class="tabs" v-if="authStore.user.userId == route.params.id">
        <span :class="['tab', { active: selectedTab === 'feed' }]" @click="selectTab('feed')">피드</span>
        <span :class="['tab', { active: selectedTab === 'analysis' }]" @click="selectTab('analysis')">분석</span>
        <span :class="['tab', { active: selectedTab === 'setting' }]" @click="selectTab('setting')">내정보수정</span>
      </div>
      <div class="main-content">
        <aside class="profile">
          <img :src="userInfo.user?.userProfileImage" alt="Profile Picture" class="profile-picture" />
          <h2 class="nickname">{{ userInfo.user?.userNickname }}</h2>
          <p>팔로잉 {{userInfo.following.length}} | 팔로워 {{userInfo.follower.length}}</p>
        </aside>
        <div class="content-area">
            <div class="content">

                <div v-if="selectedTab === 'feed'" class="feed">
                    <div class="workout-streak">
                        <h3>연속 0 일 운동중!</h3>
                            <div class="grid">
                                <div>
                                    <!-- 툴팁이나 추가 정보를 표시할 수 있습니다 -->
                                </div>
                            </div>
                    </div>

                    <div class="my-routine">
                        <h3>운동 루틴</h3>
                        <div style="justify-content: end;" v-if="authStore.user.userId == route.params.id">
                            <button class="see-all-routi">더보기</button>
                            <button class="add-routine">운동 추가하기</button>
                        </div>
                        <div class="routine-component" v-for="routine in userInfo.routines">
                          <p class="routine-name">{{ routine.routineName }}</p>
                          <!-- {{routine.exercises}}
                          <br> -->
                          <span v-for="exercise in routine.exercises">
                            {{ exercise.exerciseName }} |
                          </span>
                        </div>
                        <!-- <RoutineComponent/>
                        
                        <RoutineComponent/>
                        
                        <RoutineComponent/>
                        
                        <RoutineComponent/> -->
                    </div>

                    <div class="social-mypost row">
                        <h3>#오운완</h3>
                        <img v-for="post in userInfo.myPost" :src="`/`+post.board.boardImgUrl" class="col-4"/>
                    </div>

                </div>


                <div v-if="selectedTab === 'analysis'" class="analysis">
                <!-- Analysis content here -->
                </div>


                <div v-if="selectedTab === 'setting'" class="setting">
                    <div class="row">
                        <div class="col-auto">
                            <img :src="authStore.user.userProfileImage">
                        </div>
                        <div class="col">
                            <div class="row">
                                <label for="changeNickname">닉네임</label>
                                <input type="text" id="changeNickname" :value="authStore.user.userNickname">                            
                            </div>
                            <div class="row">
                                <div class="col-2">
                                    <button>등록</button>
                                </div>
                                
                            </div>
                        </div>
                    </div>
                    <div class="point-history">
                        <h4>포인트 내역</h4>
                    </div>
                </div>



          </div>

        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { useSocialStore } from '@/stores/social';
  import { useAuthStore } from '@/stores/auth';
  import { useRoute } from 'vue-router'
  import { routeLocationKey } from 'vue-router';
  import RoutineComponent from '@/components/info/RoutineComponent.vue';
  import SocialPostComponent from '@/components/social/SocialPostComponent.vue';
  import router from '@/router';
  import axios from 'axios';



  const route = useRoute()
  const selectedTab = ref('feed');
  const selectTab = (tab) => {
    selectedTab.value = tab;
  };
  const userId = route.params.id;
  const userInfo = ref({
    user: null,
    following: [],
    follower: [],
    routines:[],
    myPost: null
  })
  
  const socialStore = useSocialStore();
  const authStore = useAuthStore();

  console.log(route.params.id)
  console.log(authStore.user.userId)

  //유저정보
  const getUserInfo = () => {
    axios.get('http://localhost:8080/users/'+userId)
    .then((response) => {
      userInfo.value.user = response.data;
      console.log(userInfo.value.user)
    })
  }

  //팔로잉팔로워
  const getFollowingFollowers = () => {
    axios.get('http://localhost:8080/follows/following/'+userId)
    .then((response) => {
      userInfo.value.following = response.data
    })
    axios.get('http://localhost:8080/follows/followers/'+userId)
    .then((response) => {
      userInfo.value.follower = response.data
    })
  }

  //루틴보기
  const getRoutines = () => {
    axios.get('http://localhost:8080/routines/users/'+userId)
    .then((response) => {
      userInfo.value.routines = response.data.map(routine => ({
        ...routine,
        exercises: routine.exercises || []  // Ensure exercises is an array
      }));
    })
  }

  //작성글 보기
  const getUserPost = (userId) => {
    axios.get('http://localhost:8080/boards/user/'+userId)
    .then((response) => {
      userInfo.value.myPost = response.data
      // console.log(myPost)
    })
  }

  onMounted(() => {
    getUserPost(userId)
    getFollowingFollowers()
    getUserInfo()
    getRoutines()
  })
  </script>
  
  
  <style scoped>
  .profile-page {
    display: flex;
    flex-direction: column;
    gap: 1rem;
    padding: 1rem;
  }
  
  .header {
    background-color: #f5f5f5;
    padding: 1rem;
    text-align: center;
  }
  
  .tabs {
    display: flex;
    justify-content: space-around;
    background-color: #eaeaea;
    padding: 0.5rem 0;
  }
  
  .tab {
    cursor: pointer;
    padding: 0.5rem 1rem;
  }
  
  .tab.active {
    font-weight: bold;
    border-bottom: 2px solid #000;
  }
  
  .main-content {
    display: flex;
  }
  
  .profile {
    flex: 0 0 400px;
    display: flex;
    flex-direction: column;
    align-items: center;
    /* background-color: #eaeaea; */
    padding: 1rem;
    margin-right: 1rem;
  }
  
  .profile-picture {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    background-color: #ddd;
  }
  
  .nickname {
    margin-top: 1rem;
    font-size: 1.5rem;
  }
  
  .content-area {
    flex: 1;
  }
  
  .content {
    padding: 1rem;
  }
  
  .hashtags {
    padding: 1rem;
  }
  
  .routine-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 1rem;
  }
  
  .routine-item {
    background-color: #f0f0f0;
    padding: 1rem;
    text-align: center;
  }

  .grid {
    height: 150px;
    border: 1px solid black;
    background-color: grey;
  }
  </style>
  
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
                      <div class="workout-yet">
                        <p>아직 운동 인증을 하지 않았어요!</p>
                        <button class="btn">운동 인증하기</button>
                      </div>
                        <h3>올해 0일 째 운동중!</h3>
                        <div class="grid">
                          <!-- <UserStreakComponent/> -->

                          <div class="calendar" id="calendar">
      <div
        v-for="(day, index) in days"
        :key="index"
        class="day"
        :class="{ filled: isFilled(day.dateStr) }"
        :style="{ gridColumnStart: getColumn(index), gridRowStart: getRow(index) }"
      >
        <div class="tooltip">{{ day.dateStr }}</div>
      </div>
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
  import UserStreakComponent from '@/components/user/UserStreakComponent.vue'



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



  const records = [
  { id: 41, user_id: 41, board_id: 48, title: '나다', date: '2024-05-20' },
  { id: 40, user_id: 41, board_id: 10, title: 'Outdoor workout', date: '2024-05-18' },
  { id: 39, user_id: 41, board_id: 9, title: 'Gym day!', date: '2024-05-17' },
  { id: 38, user_id: 41, board_id: 8, title: 'Healthy lifestyle', date: '2024-05-16' },
  { id: 37, user_id: 41, board_id: 7, title: 'Workout tips', date: '2024-05-14' },
  { id: 36, user_id: 41, board_id: 6, title: 'My favorite exercises', date: '2024-05-12' },
  { id: 35, user_id: 41, board_id: 5, title: 'Here\'s my progress', date: '2024-05-11' },
  { id: 34, user_id: 41, board_id: 4, title: 'Feeling great after this workout', date: '2024-05-10' },
  { id: 33, user_id: 41, board_id: 3, title: 'Loving the new exercises', date: '2024-05-08' },
  { id: 32, user_id: 41, board_id: 2, title: 'Just finished a tough session', date: '2024-05-07' },
  { id: 31, user_id: 41, board_id: 1, title: 'Check out my new workout routine!', date: '2024-05-06' },
  { id: 30, user_id: 41, board_id: 10, title: '야외에서의 운동!', date: '2024-05-05' },
  { id: 29, user_id: 41, board_id: 9, title: '체육관에서의 하루!', date: '2024-05-04' }
];

const startDate = new Date(2024, 0, 1); // January 1, 2024
const daysToDisplay = 366; // Leap year
const days = ref([]);

const oneDay = 24 * 60 * 60 * 1000;

for (let i = 0; i < daysToDisplay; i++) {
  const currentDate = new Date(startDate.getTime() + i * oneDay);
  const dateStr = currentDate.toISOString().split('T')[0];
  days.value.push({ dateStr });
}

const isFilled = (dateStr) => {
  return records.some(record => record.date === dateStr);
};

const getRow = (index) => {
  return (index % 7) + 1;
};

const getColumn = (index) => {
  return Math.floor(index / 7) + 1;
};







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
    /* background-color: grey; */
  }






  .calendar {
  display: grid;

}

.day {
  width: 100%; /* Full width of the grid cell */
  height: 100%; /* Full height of the grid cell */
  border: 0.3px solid grey; /* Thinner border */
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
}

.filled {
  background-color: pink;
}

.tooltip {
  display: none;
  position: absolute;
  bottom: 100%; /* Position above the day element */
  left: 50%;
  transform: translateX(-50%);
  background-color: #333;
  color: #fff;
  padding: 5px;
  border-radius: 3px;
  font-size: 12px;
  white-space: nowrap;
  z-index: 10;
}

.day:hover .tooltip {
  display: block;
}
  </style>
  
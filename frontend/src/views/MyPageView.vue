<template>
  <div class="profile-page container">
      <div class="tabs" v-if="authStore.user.userId == route.params.id">
          <span :class="['tab', { active: selectedTab === 'feed' }]" @click="selectTab('feed')">피드</span>
          <span :class="['tab', { active: selectedTab === 'analysis' }]" @click="selectTab('analysis')">분석</span>
          <span :class="['tab', { active: selectedTab === 'setting' }]" @click="selectTab('setting')">내정보수정</span>
      </div>
      <div class="main-content">
          <aside class="profile">
              <img :src="userInfo.user?.userProfileImage" alt="Profile Picture" class="profile-picture object-fit-cover" />
              <h2 class="nickname">{{ userInfo.user?.userNickname }}</h2>
              <p>팔로잉 {{userInfo.following.length}} | 팔로워 {{userInfo.follower.length}}</p>
          </aside>
          <div class="content-area">
              <div class="content">
                  <div v-if="selectedTab === 'feed'" class="feed">
                      <div class="workout-streak">
                          <div v-if="!hasTodayPost">
                              <p>아직 운동 인증을 하지 않았어요!</p>
                              <button class="btn" @click="showUploadModal">운동 인증하기</button>
                          </div>
                          <h3>올해 {{ userInfo.myPost?.length }} 일 째 운동중!</h3>
                          <UserStreakComponent/>
                      </div>
                      <div class="my-routine">
                          <h3>운동 루틴</h3>
                          <div style="justify-content: end;" v-if="authStore.user.userId == route.params.id">
                              <button class="see-all-routi">더보기</button>
                              <button class="add-routine">운동 추가하기</button>
                          </div>
                          <div class="routine-component" v-for="routine in userInfo.routines">
                              <p class="routine-name">{{ routine.routineName }}</p>
                              <span v-for="exercise in routine.exercises">
                                  {{ exercise.exerciseName }} |
                              </span>
                          </div>
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
                              <img :src="profileImageUrl || authStore.user.userProfileImage" @click="changeProfileImg" class="profile-image object-fit-cover">
                              <input type="file" ref="profileImageInput" @change="onProfileImageChange" style="display: none;">
                          </div>
                          <div class="col">
                              <div class="row">
                                  <label for="changeNickname">닉네임</label>
                                  <input type="text" id="changeNickname" v-model="newNickname">
                              </div>
                              <div class="row">
                                  <div class="col-2">
                                      <button @click="saveProfile">등록</button>
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

      <!-- Upload Modal -->
      <div class="modal fade" id="uploadModal" tabindex="-1" aria-labelledby="uploadModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-lg">
          <div class="modal-content">
              <div class="modal-header">
                  <h5 class="modal-title" id="uploadModalLabel">Upload Image</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                  <label class="visibility-toggle">
                      <input type="checkbox" v-model="writeBoard.boardVisibility">
                      <svg viewBox="0 0 576 512" height="1em" xmlns="http://www.w3.org/2000/svg" class="lock-open">
                          <path d="M352 144c0-44.2 35.8-80 80-80s80 35.8 80 80v48c0 17.7 14.3 32 32 32s32-14.3 32-32V144C576 64.5 511.5 0 432 0S288 64.5 288 144v48H64c-35.3 0-64 28.7-64 64V448c0 35.3 28.7 64 64 64H384c35.3 0 64-28.7 64-64V256c0-35.3-28.7-64-64-64H352V144z"></path>
                      </svg>
                      <svg viewBox="0 0 448 512" height="1em" xmlns="http://www.w3.org/2000/svg" class="lock">
                          <path d="M144 144v48H304V144c0-44.2-35.8-80-80-80s-80 35.8-80 80zM80 192V144C80 64.5 144.5 0 224 0s144 64.5 144 144v48h16c35.3 0 64 28.7 64 64V448c0 35.3-28.7 64-64 64H64c-35.3 0-64-28.7-64-64V256c0-35.3 28.7-64 64-64H80z"></path>
                      </svg>
                  </label>
                  <div class="routine-list-container">
                      <div class="routine-list">
                          <div v-for="routine in userInfo.routines" :key="routine.routineId" class="form-check">
                              <input class="form-check-input" type="radio" name="routine" :id="`routine${routine.routineId}`" :value="routine.routineId" v-model="writeBoard.routineId">
                              <label class="form-check-label" :for="`routine${routine.routineId}`">
                                  {{ routine.routineName }}
                                  <ul>
                                      <li v-for="exercise in routine.exercises" :key="exercise.exerciseId">
                                          {{ exercise.exerciseName }}
                                      </li>
                                  </ul>
                              </label>
                          </div>
                      </div>
                  </div>
                  <div class="mb-3">
                      <label for="file" class="labelFile">
                          <span>
                              <svg xml:space="preserve" viewBox="0 0 184.69 184.69" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/2000/svg" id="Capa_1" version="1.1" width="60px" height="60px">
                                  <g>
                                      <g>
                                          <g>
                                              <path d="M149.968,50.186c-8.017-14.308-23.796-22.515-40.717-19.813
                                                  C102.609,16.43,88.713,7.576,73.087,7.576c-22.117,0-40.112,17.994-40.112,40.115c0,0.913,0.036,1.854,0.118,2.834
                                                  C14.004,54.875,0,72.11,0,91.959c0,23.456,19.082,42.535,42.538,42.535h33.623v-7.025H42.538
                                                  c-19.583,0-35.509-15.929-35.509-35.509c0-17.526,13.084-32.621,30.442-35.105c0.931-0.132,1.768-0.633,2.326-1.392
                                                  c0.555-0.755,0.795-1.704,0.644-2.63c-0.297-1.904-0.447-3.582-0.447-5.139c0-18.249,14.852-33.094,33.094-33.094
                                                  c13.703,0,25.789,8.26,30.803,21.04c0.63,1.621,2.351,2.534,4.058,2.14c15.425-3.568,29.919,3.883,36.604,17.168
                                                  c0.508,1.027,1.503,1.736,2.641,1.897c17.368,2.473,30.481,17.569,30.481,35.112c0,19.58-15.937,35.509-35.52,35.509H97.391
                                                  v7.025h44.761c23.459,0,42.538-19.079,42.538-42.535C184.69,71.545,169.884,53.901,149.968,50.186z"
                                                  style="fill:#010002;"></path>
                                          </g>
                                          <g>
                                              <path d="M108.586,90.201c1.406-1.403,1.406-3.672,0-5.075L88.541,65.078
                                                  c-0.701-0.698-1.614-1.045-2.534-1.045l-0.064,0.011c-0.018,0-0.036-0.011-0.054-0.011c-0.931,0-1.85,0.361-2.534,1.045
                                                  L63.31,85.127c-1.403,1.403-1.403,3.672,0,5.075c1.403,1.406,3.672,1.406,5.075,0L82.296,76.29v97.227
                                                  c0,1.99,1.603,3.597,3.593,3.597c1.979,0,3.59-1.607,3.59-3.597V76.165l14.033,14.036
                                                  C104.91,91.608,107.183,91.608,108.586,90.201z"
                                                  style="fill:#010002;"></path>
                                          </g>
                                      </g>
                                  </g>
                              </svg>
                          </span>
                      </label>
                      <input class="input" name="text" id="file" type="file" @change="onFileChange" />
                  </div>
                  <div v-if="imageUrl">
                      <img :src="imageUrl" class="img-fluidr" alt="Image preview">
                  </div>
                  <div class="write-form">
                      <textarea v-model="writeBoard.boardContent" class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px"></textarea>
                  </div>
                  <button class="btn btn-primary mt-3" @click="registPost">등록</button>
              </div>
          </div>
      </div>
  </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { Modal } from 'bootstrap';
import { useSocialStore } from '@/stores/social';
import { useAuthStore } from '@/stores/auth';
import { useRoute } from 'vue-router';

import axios from 'axios';
import UserStreakComponent from '@/components/user/UserStreakComponent.vue';

const socialStore = useSocialStore();
const authStore = useAuthStore();

const route = useRoute();
const selectedTab = ref('feed');
const selectTab = (tab) => {
  selectedTab.value = tab;
};

const userId = route.params.id;
const userInfo = ref({
  user: null,
  following: [],
  follower: [],
  routines: [],
  myPost: null,
});
const writeBoard = ref({
  userId: authStore.user.userId,
  routineId: 0,
  boardContent: '',
  boardVisibility: false,
});

const newNickname = ref(authStore.user.userNickname);
let uploadModal = null;
const imageUrl = ref(null);
const file = ref(null);

console.log(route.params.id);
console.log(authStore.user.userId);

// 유저정보
const getUserInfo = () => {
  axios.get('http://localhost:8080/users/' + userId).then((response) => {
      userInfo.value.user = response.data;
      console.log(userInfo.value.user);
  });
};

// 팔로잉팔로워
const getFollowingFollowers = () => {
  axios.get('http://localhost:8080/follows/following/' + userId).then((response) => {
      userInfo.value.following = response.data;
  });
  axios.get('http://localhost:8080/follows/followers/' + userId).then((response) => {
      userInfo.value.follower = response.data;
  });
};

// 루틴보기
const getRoutines = () => {
  axios.get('http://localhost:8080/routines/users/' + userId).then((response) => {
      userInfo.value.routines = response.data.map((routine) => ({
          ...routine,
          exercises: routine.exercises || [],
      }));
  });
};

// 작성글 보기
const getUserPost = (userId) => {
  axios.get('http://localhost:8080/boards/user/' + userId).then((response) => {
      userInfo.value.myPost = response.data;
      console.log(userInfo.value.myPost.length);
  });
};

// 오늘 운동 했는지 안했는지 확인
const hasTodayPost = computed(() => {
  const today = new Date().toLocaleDateString('en-CA', { timeZone: 'Asia/Seoul' });
  return userInfo.value.myPost?.some((post) => {
      const postDate = new Date(post.board.boardRegDate).toISOString().split('T')[0]
      console.log('postdate is ' + postDate + ', nowdate is ' + today);
      return postDate === today;
  });
});

// 글 작성 모달 열기
const showUploadModal = () => {
  if (!uploadModal) {
      const modalElement = document.getElementById('uploadModal');
      uploadModal = new Modal(modalElement);
  }
  uploadModal.show();
};

// 사진 첨부
const onFileChange = (e) => {
  const selectedFile = e.target.files[0];
  if (selectedFile && selectedFile.type.startsWith('image/')) {
      file.value = selectedFile;
      imageUrl.value = URL.createObjectURL(selectedFile);
  } else {
      file.value = null;
      imageUrl.value = null;
  }
};

// 글 등록하기
const registPost = () => {
  const formData = new FormData();
  formData.append('file', file.value);
  formData.append('userId', writeBoard.value.userId);
  formData.append('routineId', writeBoard.value.routineId);
  formData.append('boardContent', writeBoard.value.boardContent);
  formData.append('boardVisibility', writeBoard.value.boardVisibility);

  axios.post('http://localhost:8080/boards/', formData, {
      headers: {
          'Content-Type': 'multipart/form-data',
      },
  }).then((response) => {
      file.value = null;
      imageUrl.value = null;
      writeBoard.value.routineId = 0;
      writeBoard.value.boardContent = '';
      writeBoard.value.boardVisibility = false;
      hideUploadModal();
  }).catch((err) => {
      console.error('Error registering post:', err);
  });
};

const hideUploadModal = () => {
  if (uploadModal) {
      uploadModal.hide();
  }
};

const profileImageUrl = ref(null);
const profileImageFile = ref(null);
const profileImageInput = ref(null);

const changeProfileImg = () => {
  profileImageInput.value.click();
};

const onProfileImageChange = (e) => {
  const file = e.target.files[0];
  if (file && file.type.startsWith('image/')) {
      profileImageFile.value = file;
      profileImageUrl.value = URL.createObjectURL(file);
  } else {
      profileImageFile.value = null;
      profileImageUrl.value = null;
  }
};

const saveProfile = () => {
  const formData = new FormData();

  if (profileImageFile.value) {
      formData.append('profileImage', profileImageFile.value);
  } else {
      formData.append('profileImage', '');
  }

  formData.append('userId', authStore.user.userId);
  formData.append('nickname', newNickname.value);

  axios.post(`http://localhost:8080/users/update`, formData, {
      headers: {
          'Content-Type': 'multipart/form-data',
      },
  }).then(response => {
      profileImageFile.value = null;
      profileImageUrl.value = null;
      sessionStorage.setItem('user', JSON.stringify(response.data));
  }).catch(err => {
      console.error('Error updating profile:', err);
  });
};

onMounted(() => {
  getUserPost(userId);
  getFollowingFollowers();
  getUserInfo();
  getRoutines();
});
</script>

<style scoped>

.visibility-toggle {
  --color: #a5a5b0;
  --size: 30px;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  cursor: pointer;
  font-size: var(--size);
  user-select: none;
  fill: var(--color);
}

.visibility-toggle .lock-open {
  position: absolute;
  animation: keyframes-fill .5s;
}

.visibility-toggle .lock {
  position: absolute;
  display: none;
  animation: keyframes-fill .5s;
}

.visibility-toggle input:checked ~ .lock-open {
  display: none;
}

.visibility-toggle input:checked ~ .lock {
  display: block;
}

.visibility-toggle input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
  height: 0;
  width: 0;
}

@keyframes keyframes-fill {
  0% {
    transform: scale(0);
    opacity: 0;
  }

  50% {
    transform: scale(1.2);
  }
}

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
}
.img-fluid {
  max-width: 100%;
  height: auto;
}
.file-upload-wrapper {
  position: relative;
  display: inline-block;
}
.file-input {
  width: 0.1px;
  height: 0.1px;
  opacity: 0;
  overflow: hidden;
  position: absolute;
  z-index: -1;
}
.file-upload-label {
  display: inline-block;
  cursor: pointer;
  font-size: 1.25rem;
  color: #007bff;
  background-color: #fff;
  border: 2px solid #007bff;
  padding: 10px 20px;
  border-radius: 4px;
  transition: all 0.3s ease;
}
.file-upload-label:hover {
  background-color: #007bff;
  color: #fff;
}
.file-upload-label i {
  margin-right: 10px;
}
.menu {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}
.select-menu {
  margin: 0 10px;
  cursor: pointer;
  font-weight: bold;
}
.routine-list-container {
  max-height: 200px;
  overflow-y: auto;
  border: 1px solid #ccc;
  padding: 10px;
  border-radius: 4px;
}
.form-check {
  margin-bottom: 10px;
}
.profile-image {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  cursor: pointer;
}
.profile-image:hover {
  opacity: 0.8;
}
.input {
  max-width: 190px;
  display: none;
}
.labelFile {
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 250px;
  height: 190px;
  border: 2px dashed #ccc;
  align-items: center;
  text-align: center;
  padding: 5px;
  color: #404040;
  cursor: pointer;
}
</style>

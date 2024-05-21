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
                          <!-- {{routine.exercises}}
                          <br> -->
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
                            <input type="text" id="changeNickname" :value="authStore.user.userNickname">
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
            <div class="form-check form-switch">
              <input class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckChecked" v-model="writeBoard.boardVisibility">
              <label class="form-check-label" for="flexSwitchCheckChecked">공개</label>
            </div>
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
              <div class="file-upload-wrapper">
                <input type="file" id="formFile" @change="onFileChange" class="file-input">
                <label for="formFile" class="file-upload-label">
                  <i class="fas fa-cloud-upload-alt"></i>
                </label>
              </div>
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
        exercises: routine.exercises || [], // Ensure exercises is an array
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
  
    axios
      .post('http://localhost:8080/boards/', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      })
      .then((response) => {
        // 업로드 후 상태 초기화
        file.value = null;
        imageUrl.value = null;
        writeBoard.value.routineId = 0;
        writeBoard.value.boardContent = '';
        writeBoard.value.boardVisibility = false;
        hideUploadModal();
      })
      .catch((err) => {
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
  formData.append('nickname', authStore.user.userNickname);
  axios.post(`http://localhost:8080/users/update`, formData, {
      headers: {
          'Content-Type': 'multipart/form-data',
      },
  }).then(response => {
      // 업데이트 후 상태 초기화 및 사용자 정보 갱신
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
  max-height: 200px; /* 원하는 높이 설정 */
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

/* .object-fit-cover {
  object-fit: cover;
} */


.profile-image:hover {
    opacity: 0.8;
}


  </style>
  
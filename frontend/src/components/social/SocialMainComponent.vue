<template>
  <div class="container">
    <div class="menu">
      <span class="select-menu" @click="getAllPost">전체</span>
      <span class="select-menu" @click="getFollowingPost">팔로잉</span>
    </div>
    <div class="content-area">
      <ul v-if="store.returnBoardList.length != 0" class="board-list">
        <li v-for="board in store.returnBoardList" :key="board.id" class="board-item">
          <img class="board-thumbnail" src="data:image/jpeg;base64,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" @click="showBoardDetail(board)">
        </li>
      </ul>
      <p v-if="store.returnBoardList.length == 0">게시물이 없습니다.</p>
    </div>
    <button type="button" class="btn btn-primary" @click="showUploadModal">작성하기</button>

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
            <select v-model="writeBoard.routineId" class="form-select select-routine" aria-label="루틴 선택">
              <option disabled value="">루틴 선택</option>
              <option value="1">24.04.13 루틴</option>
              <option value="2">Two</option>
              <option value="3">Three</option>
            </select>
            <div class="mb-3">
              <div class="file-upload-wrapper">
                <input type="file" id="formFile" @change="onFileChange" class="file-input">
                <label for="formFile" class="file-upload-label">
                  <i class="fas fa-cloud-upload-alt"></i>
                </label>
              </div>
            </div>
            <div v-if="imageUrl">
              <img :src="imageUrl" class="img-fluid" alt="Image preview">
            </div>
            <div class="write-form">
              <textarea v-model="writeBoard.boardContent" class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px"></textarea>
            </div>
            <button class="btn btn-primary mt-3" @click="registPost">등록</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Board Detail Modal -->
    <div class="modal fade" id="boardDetailModal" tabindex="-1" aria-labelledby="boardDetailModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="boardDetailModalLabel">Board Details</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net%2F20240409_165%2F1712589691802pbsAg_JPEG%2F113725580504775095_1890594520.jpeg&type=sc960_832" class="img-fluid" alt="Board Image">
            <p>{{ selectedBoard?.boardContent }}</p>
            <p class="board-detail-regdate">{{ selectedBoard?.boardRegDate }}</p>
          </div>
          <div class="board-like">
            <div title="Like" class="heart-container">
              <input id="Give-It-An-Id" class="checkbox" type="checkbox" :checked="hasUserLiked" @click="boardLike(selectedBoard?.boardId)">
              <div class="svg-container">
                <svg xmlns="http://www.w3.org/2000/svg" class="svg-outline" viewBox="0 0 24 24">
                  <path d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z">
                  </path>
                </svg>
                <svg xmlns="http://www.w3.org/2000/svg" class="svg-filled" viewBox="0 0 24 24">
                  <path d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z">
                  </path>
                </svg>
                <svg xmlns="http://www.w3.org/2000/svg" height="100" width="100" class="svg-celebrate">
                  <polygon points="10,10 20,20"></polygon>
                  <polygon points="10,50 20,50"></polygon>
                  <polygon points="20,80 30,70"></polygon>
                  <polygon points="90,10 80,20"></polygon>
                  <polygon points="90,50 80,50"></polygon>
                  <polygon points="80,80 70,70"></polygon>
                </svg>
              </div>
              <p class="board-like-count">{{ boardLikes.length }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { Modal } from 'bootstrap';
import axios from 'axios';
import { useSocialStore } from '@/stores/social';
import { useAuthStore } from '@/stores/auth'; // Assuming you have an auth store

const store = useSocialStore();
const authStore = useAuthStore();

onMounted(() => {
  store.getAllBoardList();
});

const file = ref(null);
const imageUrl = ref(null);
let uploadModal = null;
let boardDetailModal = null;
const selectedBoard = ref(null);
const boardLikes = ref([]);
const userId = authStore.user.userId
const writeBoard = ref({
  userId: userId,
  routineId: 0,
  boardContent: '',
  boardVisibility: false
})

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

const registPost = () => {
  console.log(writeBoard.value);

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
  })
  .then((response) => {
    console.log('Post registered successfully:', response.data);
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

const showUploadModal = () => {
  if (!uploadModal) {
    const modalElement = document.getElementById('uploadModal');
    uploadModal = new Modal(modalElement);
  }
  uploadModal.show();
};

const hideUploadModal = () => {
  if (uploadModal) {
    uploadModal.hide();
  }
};

const showBoardDetail = async (board) => {
  console.log(board)
  selectedBoard.value = board.board;
  try {
    const response = await axios.get(`http://localhost:8080/boardlikes/${board.board.boardId}`);
    boardLikes.value = response.data;
  } catch (error) {
    console.error('Error fetching board likes:', error);
  }

  if (!boardDetailModal) {
    const modalElement = document.getElementById('boardDetailModal');
    boardDetailModal = new Modal(modalElement);
  }
  boardDetailModal.show();
};

const getAllPost = () => {
  store.getAllBoardList();
};

const getFollowingPost = () => {
  store.getFollowingBoardList();
};

const boardLike = (boardId) => {
  const userId = authStore.user.userId;

  axios({
    url: `http://localhost:8080/boardlikes/`,
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: { boardId, userId }
  })
    .then((response) => {
      const liked = response.data.liked;
      if (liked) {
        boardLikes.value.push({ user_id: userId });
      } else {
        boardLikes.value = boardLikes.value.filter(like => like.user_id !== userId);
      }
    })
    .catch((err) => {
      console.log(err);
    });
};

const hasUserLiked = computed(() => {
  return boardLikes.value.some(like => like.user_id === authStore.user.userId);
});
</script>

<style scoped>
.container {
  margin-top: 20px;
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

.board-list {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 10px;
  padding: 0;
  list-style: none;
}

.board-item {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.board-thumbnail {
  width: 100%;
  object-fit: cover;
  cursor: pointer;
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

.heart-container {
  --heart-color: rgb(91, 173, 255);
  position: relative;
  width: 50px;
  height: 50px;
  transition: .3s;
  display: flex;
}

.heart-container .checkbox {
  position: absolute;
  width: 100%;
  height: 100%;
  opacity: 0;
  z-index: 20;
  cursor: pointer;
}

.heart-container .svg-container {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.heart-container .svg-outline,
.heart-container .svg-filled {
  fill: var(--heart-color);
  position: absolute;
}

.heart-container .svg-filled {
  animation: keyframes-svg-filled 1s;
  display: none;
}

.heart-container .svg-celebrate {
  position: absolute;
  animation: keyframes-svg-celebrate .5s;
  animation-fill-mode: forwards;
  display: none;
  stroke: var(--heart-color);
  fill: var(--heart-color);
  stroke-width: 2px;
}

.heart-container .checkbox:checked~.svg-container .svg-filled {
  display: block;
}

.heart-container .checkbox:checked~.svg-container .svg-celebrate {
  display: block;
}

@keyframes keyframes-svg-filled {
  0% {
    transform: scale(0);
  }

  25% {
    transform: scale(1.2);
  }

  50% {
    transform: scale(1);
    filter: brightness(1.5);
  }
}

@keyframes keyframes-svg-celebrate {
  0% {
    transform: scale(0);
  }

  50% {
    opacity: 1;
    filter: brightness(1.5);
  }

  100% {
    transform: scale(1.4);
    opacity: 0;
    display: none;
  }
}

.board-like-count {
  margin: auto;
}
</style>

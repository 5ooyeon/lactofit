<template>
    <div class="container">
      <div class="menu">
        <span class="select-menu" @click="getAllPost">전체</span>
        <span class="select-menu" @click="getFollowingPost">팔로잉</span>
      </div>
      <div class="content-area">
        <ul v-if="store.returnBoardList.length != 0">
            <li v-for="board in store.returnBoardList" :key="board.id">
                <h2>{{ board.title }}</h2>
                <p>{{ board.content }}</p>
            </li>
        </ul>
        <p v-if="store.returnBoardList.length == 0">게시물이 없습니다.</p>
      </div>
      <button type="button" class="btn btn-primary" @click="showModal">작성하기</button>
  
      <!-- Modal -->
      <div class="modal fade" id="uploadModal" tabindex="-1" aria-labelledby="uploadModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="uploadModalLabel">Upload Image</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-check form-switch">
                    <input class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckChecked" checked>
                    <label class="form-check-label" for="flexSwitchCheckChecked">공개</label>
                </div>
                <select v-model="selectedRoutine" class="form-select select-routine" aria-label="루틴 선택">
                    <option disabled value="">루틴 선택</option>
                    <option value="24.04.13 루틴">24.04.13 루틴</option>
                    <option value="Two">Two</option>
                    <option value="Three">Three</option>
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
                <textarea v-model="comment" class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px"></textarea>
              </div>
              <button type="submit" class="btn btn-primary mt-3" @click="registPost">등록</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
<script setup>
import { ref, onMounted } from 'vue';
import { Modal } from 'bootstrap';
import axios from 'axios';
import { useSocialStore } from '@/stores/social';

const store = useSocialStore();

onMounted(() => {
  store.getAllBoardList();
});

  
  const file = ref(null);
  const imageUrl = ref(null);
  const selectedRoutine = ref('');
  const comment = ref('');
  let uploadModal = null;
  
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
  
  const registPost = async () => {
    if (!file.value) {
      alert('Please select an image file to upload.');
      return;
    }
  
    if (!selectedRoutine.value) {
      alert('Please select a routine.');
      return;
    }
  
    if (!comment.value) {
      alert('Please enter a comment.');
      return;
    }
  
    const formData = new FormData();
    formData.append('image', file.value);
    formData.append('routine', selectedRoutine.value);
    formData.append('comment', comment.value);
  
    try {
      const response = await axios.post('/api/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      console.log('Post registered successfully:', response.data);
      // Clear the form after successful upload
      file.value = null;
      imageUrl.value = null;
      selectedRoutine.value = '';
      comment.value = '';
      hideModal();
    } catch (error) {
      console.error('Error registering post:', error);
    }
  };
  
  const showModal = () => {
    if (!uploadModal) {
      const modalElement = document.getElementById('uploadModal');
      uploadModal = new Modal(modalElement);
    }
    uploadModal.show();
  };
  
  const hideModal = () => {
    if (uploadModal) {
      uploadModal.hide();
    }
  };

  const getAllPost = () => {
      store.getAllBoardList();
  }

  const getFollowingPost = () => {
    store.getFollowingBoardList();
  }

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
  </style>
  
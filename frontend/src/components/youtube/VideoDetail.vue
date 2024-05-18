<template>
    <div class="container">
        <p class="video-title">
            {{ lastVideo.snippet.title }}
        </p>
        <div class="video-src">
            <iframe :src="`https://www.youtube.com/embed/${lastVideo.id.videoId}`" frameborder="0" allowfullscreen></iframe>
        </div>

        <div class="comment-section">
            <p class="border-bottom pb-2 mb-0">Comments ( {{ store.commentList.length }} )</p>
            <div class="comment-write-form">
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" v-model="myComment"></textarea>
                <button class="write-comment btn btn-primary" @click="writeComment()">
                    입력
                </button>
            </div>
            <CommentList v-for="comment in store.commentList" :key="comment.id" :comment="comment"/>
        </div>
    </div>

</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useYoutubeStore } from '@/stores/youtube';
import { useAuthStore } from '@/stores/auth';
import CommentList from './CommentList.vue';

const store = useYoutubeStore();
const authStore = useAuthStore()
const myComment = ref()

// Computed property to get the last video from history
const lastVideo = computed(() => {
    if (store.videoHistory.length > 0) {
        return store.videoHistory[store.videoHistory.length - 1];
    }
    return null;  // Return null to handle checks more consistently
});

const writeComment = () => {
  if (myComment.value.trim() === '') {
    return; // Do not allow empty comments
  }
  store.writeComment(myComment.value, lastVideo.value.id.videoId)
//   console.log('댓글: '+myComment.value)
//   console.log(lastVideo.value.id.videoId)
//   console.log('user access token: '+authStore.user.accessToken)
  myComment.value = '';
}
</script>

<style scoped>
.container {
    margin-top: 20px;
}
</style>

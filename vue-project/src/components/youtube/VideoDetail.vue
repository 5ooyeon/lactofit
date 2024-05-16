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
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
            </div>
            <CommentList v-for="comment in store.commentList" :key="comment.id" :comment="comment"/>
        </div>
    </div>

</template>

<script setup>
import { ref, onMounted, computed, watch } from 'vue';
import { useYoutubeStore } from '@/stores/youtube';
import CommentList from './CommentList.vue';

const store = useYoutubeStore();

// Computed property to get the last video from history
const lastVideo = computed(() => {
    if (store.videoHistory.length > 0) {
        return store.videoHistory[store.videoHistory.length - 1];
    }
    return null;  // Return null to handle checks more consistently
});

</script>

<style scoped>
.container {
    margin-top: 20px;
}
</style>

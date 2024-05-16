<template>
  <div class="col-3" @click="watchVideo">
      <img :src="video.snippet.thumbnails.default.url">
      <p>{{ video.snippet.title }}</p>
  </div>
</template>

<script setup>
import { useYoutubeStore } from '@/stores/youtube';
import { defineProps } from 'vue';
import { useRouter } from 'vue-router';

const store = useYoutubeStore()

const props = defineProps({
video: {
  type: Object,
  required: true
}
})

const router = useRouter()

const watchVideo = () => {
store.addToHistory(props.video)
router.push({ name: 'VideoDetail' });
}
</script>

<style scoped>
.col-3 {
cursor: pointer;
}
</style>
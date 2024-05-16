<template>
    <div class="col-3">
        <img :src="video.snippet.thumbnails.default.url">
        <p>{{ video.snippet.title }}</p>
    </div>
</template>

<script setup>
import { useYoutubeStore } from '@/stores/youtube';
import { defineProps } from 'vue';
const store = useYoutubeStore()

const props = defineProps({
  video: {
    type: Object,
    required: true
  }
})
</script>
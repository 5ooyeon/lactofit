<template>
  <div class="container">
    <div>
      <p>최근에 본 영상</p>
      <div class="recent-videos">
        <VideoList v-for="video in store.videoHistory" :key="video.id.videoId" :video="video" />
      </div>
    </div>
    <div>
      <p>이런 운동은 어때요?</p>
      <p class="weakness-desc">가장 적게 한 <em class="my-weakness-part-video">상체</em> 운동의 영상이에요.</p>
      <div class="row weakness-videos">
        <VideoList v-for="video in store.weaknessVideos" :key="video.id.videoId" :video="video" />
      </div>
    </div>
  </div>
</template>

<script setup>
import VideoList from './VideoList.vue';
import { ref, onMounted } from 'vue';
import { useYoutubeStore } from '@/stores/youtube';
import { useRouter } from 'vue-router';

const store = useYoutubeStore();
const keyword = ref('');
const router = useRouter();

function youtubeSearch(keyword, mode) {
  store.getVideosBySearchWord(keyword, mode).then(() => {
    if (mode === 'search') {
      router.push({ name: 'SearchResultComponent' });
    }
  });
}

onMounted(() => {
    if(store.weaknessVideos.length == 0) {
        console.log('weakness')
        store.getVideosBySearchWord('상체', null)
    }
})

</script>


<style scoped>
.container {
    margin-top: 20px;
}

.recent-videos {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  /* gap: 16px; */
  margin-bottom: 20px;
}
</style>
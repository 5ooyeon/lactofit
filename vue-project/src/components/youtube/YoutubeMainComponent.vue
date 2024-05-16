<!-- <template>
    <div class="container">
        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="운동명, 파트로 검색" v-model="keyword">
            <button class="btn btn-outline-secondary" type="button" @click="youtubeSearch">Button</button>
        </div>
        <div>
            <p>최근에 본 영상</p>
            <div class="recent-videos">
                
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
import { ref, onMounted } from 'vue'
import { useYoutubeStore } from '@/stores/youtube';

const store = useYoutubeStore()

onMounted(() => {
    if(store.weaknessVideos == null) {
        store.getVideosBySearchWord('상체', null)
    }
})

const youtubeSearch = (keyword) => {
    console.log("sdf");
    store.getVideosBySearchWord(keyword, 'search')
}
</script> -->

<template>
  <div class="container">
    <div>
      <p>최근에 본 영상</p>
      <div class="recent-videos">
        <!-- Assuming VideoList accepts videos prop -->
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
</style>
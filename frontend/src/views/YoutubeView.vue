<template>
    <div class="container">
        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="운동명, 파트로 검색" v-model="keyword">
            <button class="btn btn-outline-secondary" type="button" @click="youtubeSearch(keyword, 'search')">Button</button>
        </div>
    </div>
    <RouterView/>
</template>

<script setup>
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

</script>
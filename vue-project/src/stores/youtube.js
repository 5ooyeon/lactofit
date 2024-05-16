// import { ref, computed } from 'vue'
// import { defineStore } from 'pinia'
// import axios from 'axios'

// export const useYoutubeStore = defineStore('youtube', () => {
//   const weaknessVideos = ref(null)
//   const searchedVideos = ref(null)
//   const keyword = ref(null);


// const getVideosBySearchWord = (keyword, link) => {
//     const URL = 'https://www.googleapis.com/youtube/v3/search'
//     const API_KEY = import.meta.env.VITE_YOUTUBE_API_KEY

//     console.log(link)

//     axios({
//       url: URL,
//       method: 'GET',
//       params: {
//         key: API_KEY,
//         part: 'snippet',
//         maxResults: 30,
//         q: keyword+'운동',
//         type: 'video'
//       }
//     })
//     .then((response)=>{
//         if (link) {  // 검색
//             searchedVideos.value = response.data.items
//             keyword.value = this.keyword
//             router.push({ name: 'SearchResultComponent' });  // result 경로로 이동
//         } else { //weaknessVideos
//             weaknessVideos.value = response.data.items
//         }
//         console.log(response.data)
//     })
//     .catch(()=>{})



// }

//   return { weaknessVideos, getVideosBySearchWord,searchedVideos,keyword }
// })

import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

export const useYoutubeStore = defineStore('youtube', () => {
  const weaknessVideos = ref([])
  const searchedVideos = ref([])
  const searchWord = ref(null);
  const isLoading = ref(false);

  const getVideosBySearchWord = async (keyword, mode) => {
    isLoading.value = true;
    const URL = 'https://www.googleapis.com/youtube/v3/search';
    const API_KEY = import.meta.env.VITE_YOUTUBE_API_KEY;

    console.log('start--')

    try {
      const response = await axios.get(URL, {
        params: {
          key: API_KEY,
          part: 'snippet',
          maxResults: 30,
          q: keyword + '운동',
          type: 'video'
        }
      });
      console.log('success')
      if (mode === 'search') {
        console.log('search Method')
        searchedVideos.value = response.data.items;
        searchWord.value = keyword
      } else {
        console.log('default loading')
        weaknessVideos.value = response.data.items;
      }
      console.log(response.data);
    } catch (error) {
        console.log('error')
      console.error('Error fetching videos:', error);
    } finally {
      isLoading.value = false;
    }
  }

  return { weaknessVideos, searchedVideos, getVideosBySearchWord, isLoading, searchWord };
});

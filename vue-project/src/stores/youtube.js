import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const API_KEY = import.meta.env.VITE_YOUTUBE_API_KEY;
const CommentURL = 'https://www.googleapis.com/youtube/v3/commentThreads';

export const useYoutubeStore = defineStore('youtube', () => {
  const weaknessVideos = ref([])
  const searchedVideos = ref([])
  const searchWord = ref(null)
  const videoHistory = ref([])
  const commentList = ref([])
//   const isLoading = ref(false);

  const getVideosBySearchWord = async (keyword, mode) => {
    const URL = 'https://www.googleapis.com/youtube/v3/search';


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
    //   isLoading.value = false;
    }
  }

  const addToHistory = (video) => {
    console.log("1231231231232123")
    videoHistory.value.push(video);
    console.log(video.id.videoId)

    axios.get(CommentURL, {
        params: {
          part: 'snippet',
          videoId: video.id.videoId,
          key: API_KEY
        }
      }).then((response) => {
        commentList.value = response.data.items
        console.log(commentList.value)
      })
  }

  return { weaknessVideos, searchedVideos, getVideosBySearchWord, searchWord, addToHistory, videoHistory, commentList  };
});

// src/stores/social.js
import { defineStore } from 'pinia';
import axios from 'axios';
import { ref } from 'vue';

const REST_BOARD_API = `http://localhost:8080/boards`

export const useSocialStore = defineStore('social', () => {
    const returnBoardList = ref([])
    const getAllBoardList = () => {
      axios.get(REST_BOARD_API)
        .then((response) => {
          returnBoardList.value = response.data
        console.log(returnBoardList)
      })
    }

    const getFollowingBoardList = () => {
      const user = JSON.parse(sessionStorage.getItem('user'))
      console.log(user.user_id)
      axios.get(REST_BOARD_API+`/following/`+user.user_id)
      .then((response) => {
        returnBoardList.value = response.data
      })
    }

    return{
        returnBoardList,
        getFollowingBoardList,
        getAllBoardList
    }
  
});

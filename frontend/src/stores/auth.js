import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

export const useAuthStore = defineStore('auth', () => {
  const clientId = import.meta.env.VITE_GOOGLE_CLIENT_ID
  const redirectUri = import.meta.env.VITE_GOOGLE_REDIRECT_URL
  const clientSecret = import.meta.env.VITE_GOOGLE_CLIENT_SECRET

  const user = ref(null)

  function loginWithGoogle() {
    const url = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code&scope=openid email profile`;
    window.location.href = url;
  }

  async function fetchUserOpenId(code) {
    try {
      const response = await axios.post('http://localhost:8080/oauth2/callback', {
        code: code,
        redirect_uri: import.meta.env.VITE_BASE_URL
      }, {
        withCredentials: true // 세션 정보를 주고 받기 위해 설정
      });
      user.value = response.data;
      sessionStorage.setItem('user', JSON.stringify(user.value));
      console.log(" 성공!!!@@@@@@@@@@@@@@@@@")
    } catch (error) {
      console.error('Failed to fetch user OpenID:', error);
    }
  }


  return { loginWithGoogle, fetchUserOpenId, user }
})

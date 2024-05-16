import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

export const useAuthStore = defineStore('auth', () => {
  const clientId = import.meta.env.VITE_GOOGLE_CLIENT_ID
  const redirectUri = import.meta.env.VITE_GOOGLE_REDIRECT_URL
  const userOpenId = ref(null)

  function loginWithGoogle() {
    const url = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code&scope=openid email profile`;
    window.location.href = url;
  }

  async function fetchUserOpenId(code) {
    // try {
    //   const response = await axios.post('YOUR_BACKEND_ENDPOINT', {
    //     code,
    //     redirect_uri: redirectUri,
    //   });
    //   userOpenId.value = response.data.openid;
    // } catch (error) {
    //   console.error('Failed to fetch user OpenID:', error);
    // }
    console.log('login정보 백앤드로 넘겨주기')
  }

  return { loginWithGoogle, fetchUserOpenId, userOpenId }
})

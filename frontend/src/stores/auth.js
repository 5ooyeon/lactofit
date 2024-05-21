import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'
import axios from 'axios'

export const useAuthStore = defineStore('auth', () => {
  const user = ref(JSON.parse(sessionStorage.getItem('user')))
  const token = ref(sessionStorage.getItem('jwtToken'))
  const router = useRouter()

  function loginWithGoogle() {
    const clientId = import.meta.env.VITE_GOOGLE_CLIENT_ID
    const redirectUri = import.meta.env.VITE_GOOGLE_REDIRECT_URL
    const url = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code&scope=openid email profile https://www.googleapis.com/auth/youtube.force-ssl`;
    window.location.href = url;
  }

  async function fetchUserOpenId(code) {
    const redirectUri = import.meta.env.VITE_GOOGLE_REDIRECT_URL
    try {
      const response = await axios.post('http://localhost:8080/oauth2/callback', {
        code: code,
        redirect_uri: redirectUri
      }, {
        withCredentials: true // 세션 정보를 주고 받기 위해 설정
      });

      sessionStorage.setItem('user', JSON.stringify(response.data.user));
      sessionStorage.setItem('jwtToken', response.data.jwtToken);
      user.value = response.data.user;
      token.value = response.data.jwtToken;
      console.log(user.value);
      router.push("/");
      console.log("성공!!!@@@@@@@@@@@@@@@@@");
    } catch (error) {
      console.error('Failed to fetch user OpenID:', error.response ? error.response.data : error.message);
    }
  }

  function logout() {
    user.value = null
    token.value = null
    sessionStorage.removeItem('user')
    sessionStorage.removeItem('jwtToken')
    router.push('/') // 로그인 페이지로 이동
  }

  // Axios 인터셉터 설정
  axios.interceptors.request.use(
    config => {
      if (token.value) {
        config.headers.Authorization = `Bearer ${token.value}`;
      }
      return config;
    },
    error => {
      return Promise.reject(error);
    }
  );

  return { loginWithGoogle, fetchUserOpenId, logout, user: computed(() => user.value), token: computed(() => token.value) }
})

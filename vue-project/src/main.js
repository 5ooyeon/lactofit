import "bootstrap/dist/css/bootstrap.min.css"


import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(createPinia())
app.use(router)


// import GAuth from 'vue-google-oauth2'

// const gauthOption = {
//     clientId: '687528544216-7pd3sli2jp8jo49il22uvgm0qn7i8nlc.apps.googleusercontent.com',
//     scope: 'profile email openid',  // 사용자의 프로필, 이메일, OIDC 접근을 요청
//     prompt: 'consent',              // 사용자의 명시적인 동의를 매번 요청
//     fetch_basic_profile: true       // 기본 프로필 정보를 자동으로 가져오기
// }
  

// app.use(GAuth, gauthOption)

// import googleAuth from './authentification';
// app.use(GAuth, {
//     clientId: process.env.VUE_APP_OAUTH_CLIENT,
//     scope: 'profile email https://www.googleapis.com/auth/plus.login'
// });
  
//   // set auth config
// const prompt = 'select_account'
// const GoogleAuthConfig = Object.assign({ scope: 'profile email' }, {
//     clientId: process.env.VUE_APP_OAUTH_CLIENT,
//     scope: 'profile email https://www.googleapis.com/auth/plus.login',
// });
  
//   // Install Vue plugin
// app.config.globalProperties.$gAuth = googleAuth;
// app.config.globalProperties.$gAuth.load(GoogleAuthConfig, prompt)

app.mount('#app')

import "bootstrap/dist/js/bootstrap.min.js"
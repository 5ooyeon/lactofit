import LoginComponent from '@/components/user/LoginComponent.vue'
import UserView from '@/views/UserView.vue'
import MainView from '@/views/MainView.vue'
import { createRouter, createWebHashHistory } from 'vue-router' // createWebHashHistory 사용
import SignupComponent from '@/components/user/SignupComponent.vue'
import UserDetailComponent from '@/components/user/UserDetailComponent.vue'
import SearchResultComponent from '@/components/youtube/SearchResultComponent.vue'
import YoutubeView from '@/views/YoutubeView.vue'
import YoutubeMainComponent from '@/components/youtube/YoutubeMainComponent.vue'
import VideoDetail from '@/components/youtube/VideoDetail.vue'

const router = createRouter({
  history: createWebHashHistory(), // 변경된 부분: createWebHistory -> createWebHashHistory
  routes: [
    {
      path: "/",
      name: "Main",
      component: MainView,
    },
    {
      path: "/user",
      name: "User",
      component: UserView,
      children: [
        {
          path: "signin",
          name: "LoginComponent",
          component: LoginComponent
        },
        {
          path: "signup",
          name: "SignupComponent",
          component: SignupComponent
        },
        {
          path: "mypage",
          name: "UserDetailComponent",
          component: UserDetailComponent
        },
      ]
    },
    {
      path: "/youtube",
      name: "Youtube",
      component: YoutubeView,
      children: [
        {
          path: "",
          name: "YoutubeMainComponent",
          component: YoutubeMainComponent
        },
        {
          path: "result",
          name: "SearchResultComponent",
          component: SearchResultComponent
        },
        {
          path: "detail",
          name: "VideoDetail",
          component: VideoDetail
        },
      ]
    },
  ]
})

export default router;

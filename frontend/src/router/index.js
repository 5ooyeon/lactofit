
import UserView from '@/views/UserView.vue'
import MainView from '@/views/MainView.vue'
import { createRouter, createWebHistory } from 'vue-router' // createWebHashHistory 사용

import SearchResultComponent from '@/components/youtube/SearchResultComponent.vue'
import YoutubeView from '@/views/YoutubeView.vue'
import YoutubeMainComponent from '@/components/youtube/YoutubeMainComponent.vue'
import VideoDetail from '@/components/youtube/VideoDetail.vue'
import SocialView from '@/views/SocialView.vue'
import SocialMainComponent from '@/components/social/SocialMainComponent.vue'
import MyPageView from '@/views/MyPageView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
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
          path: ':id',
          name: "MyPageView",
          component: MyPageView
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
    {
      path: "/social",
      name: "Social",
      component: SocialView,
      children: [
        {
          path: "",
          name: "SocialMainComponent",
          component: SocialMainComponent
        },
        {
          path: "",
          name: "SocialMainComponent",
          component: SocialMainComponent
        },
      ]
    },
  ]
})

export default router;


import UserView from '@/views/UserView.vue'
import MainView from '@/views/MainView.vue'
import { createRouter, createWebHistory } from 'vue-router' // createWebHashHistory 사용

import SearchResultComponent from '@/components/youtube/SearchResultComponent.vue'
import YoutubeView from '@/views/YoutubeView.vue'
import YoutubeMainComponent from '@/components/youtube/YoutubeMainComponent.vue'
import VideoDetail from '@/components/youtube/VideoDetail.vue'
import TempComponent from '@/components/user/tempComponent.vue'

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
          path: "loading",
          name: "TempComponent",
          component: TempComponent
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

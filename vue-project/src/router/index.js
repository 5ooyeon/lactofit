import LoginComponent from '@/components/user/LoginComponent.vue'
import UserView from '@/views/UserView.vue'
import MainView from '@/views/MainView.vue'
import { createRouter, createWebHistory } from 'vue-router'
import SignupComponent from '@/components/user/SignupComponent.vue'
import UserDetailComponent from '@/components/user/UserDetailComponent.vue'

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
  ]
})

export default router

import LoginField from '@/components/login/LoginField.vue'
import LoginView from '@/views/LoginView.vue'
import MainView from '@/views/MainView.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "Main",
      component: MainView,
    },
    {
      path: "/signin",
      name: "Login",
      component: LoginView,
      children: [
        {
          path: "",
          name: "LoginField",
          component: LoginField
        }
      ]
    },
  ]
})

export default router

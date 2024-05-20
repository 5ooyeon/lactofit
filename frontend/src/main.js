import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

// Bootstrap CSS
import 'bootstrap/dist/css/bootstrap.min.css'

import '@mdi/font/css/materialdesignicons.css'

// Bootstrap JavaScript
import 'bootstrap/dist/js/bootstrap.bundle'

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')

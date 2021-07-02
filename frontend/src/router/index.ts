import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import FotoGalerieView from '@/views/FotoGalerieView.vue'
import AboutView from '@/views/AboutView.vue'
import FotoDetailView from '@/views/FotoDetailView.vue'
import LoginView from '@/views/LoginView.vue'
import { useLoginStore } from '@/services/LoginStore';

const {loginstate} = useLoginStore();
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'FotoGalerieView',
    component: FotoGalerieView
  },
  {
    path: '/about',
    name: 'AboutView',
    component: AboutView
  },
  {
    path: '/detail/:fotoid',
    name: 'FotoDetailView',
    component: FotoDetailView,
    props: true
  },
  {
    path: '/login',
    name: 'Login/Logout',
    component: LoginView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to,from) =>{
  const ziel = to.fullPath;
  if(!loginstate.isLoggedIn && ziel !== '/login') return '/login'
})

export default router

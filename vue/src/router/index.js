import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('../views/Manage.vue'),
    redirect: "/home",
    children: [
      { path: 'home', name: '首页', component: () => import('../views/Home.vue')},
      { path: 'user', name: '用户管理', component: () => import('../views/User.vue')},
      { path: 'file', name: '文件管理', component: () => import('../views/File.vue')},
      { path: 'person', name: '演员管理', component: () => import('../views/Person.vue')},
      { path: 'ipNodeMap', name: 'ip节点管理', component: () => import('../views/IpNodeMap.vue')},
      { path: 'IpNodeDetectProcess', name: 'ip节点探测过程', component: () => import('../views/IpNodeDetectProcess.vue')},
      { path: 'IpKeyNodeMap', name: 'ip关键节点识别', component: () => import('../views/IpKeyNodeMap.vue')},
    ]
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('../views/About.vue')
  },
  {
    path: '/Login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  localStorage.setItem("currentPathName", to.name)  // 设置当前的路由名称，为了在Header组件中去使用
  store.commit("setPath")  // 触发store的数据更新
  next()  // 放行路由
})

export default router
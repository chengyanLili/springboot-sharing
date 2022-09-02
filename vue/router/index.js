import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

const routes = [
    {//后续的页面都会继承这个路由
        path:'/',
        name:'Main',
        component:()=>import('../src/views/Main.vue'),
        children:[
            {
                path:'/home',
                name:'home',
                component:() => import('../src/views/home.vue')
            },
            {
                path:'/user',
                name:'user',
                component:() => import('../src/views/user.vue')
            },
            {
                path:'/my',
                name:'my',
                component:() => import('../src/views/my.vue')
            },
            {
                path:'/source',
                name:'source',
                component:() => import('../src/views/source.vue')
            }
        ]
    },


]

const router = new VueRouter({
    mode:'history',
    routes
})
export default router
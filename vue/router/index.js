import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)
const originalPush = VueRouter.prototype.push

VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
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
                path:'/sourceManage',
                name:'sourceManage',
                component:() => import('../src/views/source/manage/sourceManage')
            },


        ],
    },
    {
        path:'/login',
        name:'login',
        component:() => import('../src/views/login/login')
    },
    {
        path:'/register',
        name:'register',
        component:() => import('../src/views/register/register')
    },
    {
        path:'/sourceShare',
        name:'sourceShare',
        component:() => import('@/views/frontVue/sourceShare')
    },
    {
        path:'/userSelf',
        name:'userSelf',
        component:() => import('../src/views/frontVue/userSelf')
    },
    {
        path:'/publish',
        name:'publish',
        component:() => import('../src/views/frontVue/publish')
    },


]

const router = new VueRouter({
    mode:'history',
    routes
})
export default router
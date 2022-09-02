<template>
    <el-menu default-active="1-4-1" 
        class="el-menu-vertical-demo"
        background-color="#fff"
        text-color="black"
        active-text-color="#001f97"
        @open="handleOpen" 
        @close="handleClose" 
        >
      <h3>高校资源共享平台</h3>
        <el-menu-item @click="clickMenu(item)" 
        v-for="item in noChildren" :index="item.path + ''" :key="item.path">
            <i :class="'el-icon-' + item.icon"></i>
            <span slot="title">{{ item.lable }}</span>
        </el-menu-item>
        <el-submenu v-for="item in hasChildren" :index="item.path + ''" :key="item.path">
            <template slot="title">
                <i :class="'el-icon-' + item.icon"></i>
                <span slot="title">{{ item.lable }}</span>
            </template>
            <el-menu-item-group v-for="(item, subIndex) in item.children" :key="item.path">
                <el-menu-item :index="item.path">{{ item.lable }}</el-menu-item>
            </el-menu-item-group>
        </el-submenu>
    </el-menu>
</template>

<script>
export default {
    name: 'Aside',
    data() {
        return {
            menu: [
                {
                    path: '/home',
                    name: 'home',
                    lable: '首页',
                    icon: 's-home',
                  url:'home/home'
                },
                 {
                    path: '/user',
                    name: 'user',
                    lable: '用户管理',
                    icon: 'user-solid',
                   url:'user/user'
                },
              {
                path: '/source',
                name: 'source',
                lable: '资源管理',
                icon: 'picture',
                url:'source/source'
              },
              {
                path: '/my',
                name: 'my',
                lable: '我的中心',
                icon: 's-custom',
                url:'my/my'
              },
                {
                    lable: '其他',
                    icon: 'location',
                    children: [
                        {
                            path: '/page1',
                            name: 'Page1',
                            lable: '页面1',
                            icon: 'setting',
                            url: 'Other/PageOne'
                        },
                        {
                            path: '/page2',
                            name: 'Page2',
                            lable: '页面2',
                            icon: 'setting',
                            url: 'Other/PageTwo'
                        },
                    ]
                },
            ]
        };
    },
    //用计算属性将menu中的进行筛选渲染：有子路由和没有子路由的两个路由数组（noChildren和hasChildren)
    computed: {
        noChildren() {
            return this.menu.filter(item => !item.children)
        },
        hasChildren() {
            return this.menu.filter(item => item.children)
        },
        //
        isCollapse(){
            return this.$store.state.isCollapse
        }
    },
    methods: {
        handleOpen(key, keyPath) {

        },
        handleClose(key, keyPath) {

        },
        clickMenu(item){
            this.$router.push({
                name:item.name
            })
        }
    }
}
</script>
<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
    height: 100vh;
}
h3{
    text-align: center;
    line-height: 68px;
}
</style>
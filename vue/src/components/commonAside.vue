<template>
    <el-menu default-active="1-4-1" 
        class="el-menu-vertical-demo"
        background-color="#fff"
        text-color="black"
        active-text-color="#001f97"
        @open="handleOpen" 
        @close="handleClose" 
        >
      <h3>高校教学资源共享平台</h3>
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
                <el-menu-item :index="item.path"  @click="toChildren(item)">{{ item.lable }}</el-menu-item>
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
                // {
                //     path: '/home',
                //     name: 'home',
                //     lable: '首页',
                //     icon: 's-home',
                //   url:'home/home'
                // },
              {
                path: '/sourceShare',
                name: 'sourceShare',
                lable: '资源共享',
                icon: 's-home',
                url:'share/share'
              },
                 {
                    path: '/user',
                    name: 'user',
                    lable: '用户管理',
                    icon: 'user-solid',
                   url:'user/user'
                },
              // {
              //   path: '/my',
              //   name: 'my',
              //   lable: '个人中心',
              //   icon: 's-custom',
              //   url:'my/my'
              // },
                {
                  path:'/sourceManage',
                  name:'sourceManage',
                    lable: '教学资源管理',
                    icon: 's-management',
                  url:'sourceManage/sourceManage'
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
            let hasChildren = this.menu.filter(item => item.children)
          return hasChildren
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
        },
      toChildren(item){
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
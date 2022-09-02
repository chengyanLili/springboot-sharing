<template>
    <el-container style="height:100%">
        <el-aside width="auto">
            <Aside></Aside>
        </el-aside>
        <el-container>
            <el-header>
                <Header></Header>
            </el-header>
<!--          面包屑-->
<!--          <div class="bread">-->
<!--            <el-breadcrumb separator="/">-->
<!--              <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>-->
<!--              <el-breadcrumb-item><a href="/">活动管理</a></el-breadcrumb-item>-->
<!--              <el-breadcrumb-item>活动列表</el-breadcrumb-item>-->
<!--              <el-breadcrumb-item>活动详情</el-breadcrumb-item>-->
<!--            </el-breadcrumb>-->
<!--          </div>-->
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
import Aside from '../components/commonAside.vue'
import Header from '../components/commonHeader.vue'
export default {
    name: 'Home',
    components:{
        Aside,
        Header
    },
    data() {
        return {
        }
    }
};
</script>
<style>
.el-header{
padding: 0;
}
.bread{
  margin: 10px;
}
.el-main{
    padding: 10px;
}
</style>
<template>
    <el-container style="height:100%">
        <el-aside width="auto">
            <Aside></Aside>
        </el-aside>
        <el-container>
            <el-header>
                <Header></Header>
            </el-header>
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
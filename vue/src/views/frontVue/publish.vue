<template>
  <div>
    <FrontHeader></FrontHeader>
    <div class="navs">
      <div class="nav" v-for="item in menu" :key="item.path" @click="clickMenu(item)">{{ item.lable }}</div>
    </div>
    <div>发布</div>
  </div>
</template>

<script>
import FrontHeader from "@/components/frontHeader";
export default {
  name: "userSelf",
  components:{
    FrontHeader,
  },
  data() {
    return {
      menu: [
        {
          path: '/home',
          name: 'home',
          lable: '我的上传',
          icon: 's-home',
          url:'home/home'
        },
        {
          path: '/publish',
          name: 'publish',
          lable: '发布',
          icon: 's-home',
          url:'home/home'
        },
        {
          path: '/sourceShare',
          name: 'sourceShare',
          lable: '我的点赞',
          icon: 's-home',
          url:'share/share'
        },
        {
          path: '/user',
          name: 'user',
          lable: '我的收藏',
          icon: 'user-solid',
          url:'user/user'
        },
        {
          path: '/my',
          name: 'my',
          lable: '浏览记录',
          icon: 's-custom',
          url:'my/my'
        },
      ]
    };
  },
  methods:{
    clickMenu(item){
      this.$router.push({
        name:item.name
      })
    },
  }
}
</script>

<style lang="less" scoped>
.navs{
  display: flex;
  justify-content: space-between;
  border-radius: 10px;
  background-color: #effaf4;
  margin: 10px 30%;
  .nav{
    text-align: center;
    margin:  20px;
  }
}
//.container{
//  margin: 0 10%;
//  background-color: #d3dce6;
//}

</style>
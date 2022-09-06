<template>
  <div>
    <div class="blank"></div>
    <div class="container">
      <div class="title">高校教学资源共享平台</div>
      <div class="infos">
        <el-menu background-color="white" text-color="black" color="black" :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-menu-item v-for="item in headerMenu" :key="item.path" @click="clickMenu(item)">{{ item.lable }}</el-menu-item>
        </el-menu>
        <div class="line"></div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: 'frontHeader',
  data() {
    return {
      headerMenu:[
        {
          path: '/sourceShare',
          name: 'sourceShare',
          lable: '推荐',
          url:'share/share'
        },
        {
          path: '/userSelf',
          name: 'userSelf',
          lable: '个人中心',
          url:'userSelf/userSelf'
        }, {
          path: '/home',
          name: 'home',
          lable: '后台管理',
          url:'home/home'
        },
        {
          path: '/exit',
          name: 'exit',
          lable: '退出登录',
          url:'exit/exit'
        },
      ],
      userImg: require('@/assets/img/user.jpeg'),
      collapseMenu: false,
      activeIndex: '1',
      activeIndex2: '1'
    }
  },
  methods: {
    handleMenu() {

    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    clickMenu(item){
      console.log('ss',item)
      this.$router.push({
        name:item.name
      })
    },
  }
};
</script>
<style lang="less" scoped>
.blank{
  height: 60px;
}
.container {
  z-index: 10;
  background-color: white;
  position: fixed;
  top: 0;
  border: solid 1px #c7bfbf;
  width: 100%;
  height: 60px;
  display: flex;
  justify-content: space-between;
  align-items: center;
.title{
  margin: 0 20px;
}
  .infos {
    margin: 0 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    .video{
      margin: 0 20px;
    }
    .my{
      margin: 0 20px;
    }
    .exit {
      margin: 0 20px;
      display: flex;
      justify-content: space-around;
      align-items: center;
      .el-icon-switch-button{
        margin-right: 2px;
      }
    }
  }
}

</style>
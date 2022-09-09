<template>
      <div class="contain">
    <div class="blank"></div>
        <div class="user">
          <div style="font-size: 19px;margin-right: 5px" class="el-icon-user"></div>
          <div style="font-size: 19px" class="name">{{ userInfo.username }}</div>
        </div>

        <div @click="exit" class="exit">
          <div class="el-icon-switch-button"></div>
          <div>退出登录</div>
        </div>
      </div>
</template>

<script>
export default {
    name: 'Header',
    data() {
        return {
            userImg: require('@/assets/img/user.jpeg'),
            collapseMenu:false,
          userInfo:[]
        }
    },
  created() {
let userInfo =  sessionStorage.getItem('user')
    this.userInfo =JSON.parse(userInfo)
  },
  methods:{
      exit(){
        this.$router.replace('/login')
      }
    }
};
</script>
<style lang="less">
.user{
  display: flex;
  align-items: center;
}
.user:hover{
  color: #3560d9;
}
.contain {
  border-bottom: darkgray solid 1px;
  width: 100%;
  height: 100%;
  background-color: #ffffff;
  display: flex;
  justify-content: space-around;
  align-items: center;
  .blank{
    width: 70%;
    height: 100%;
  }
  img{
    width:45px;
    height:45px;
    border-radius: 50%;
  }
  .exit:hover{
    color: #4b78ec;
  }
  .exit{
    display: flex;
    align-items: center;
    margin-right: 20px;
    .el-icon-switch-button{
      font-size: 18px;
      margin-right: 5px;
    }
    text{
      color: black;
    }
  }

}
.el-header{

}

</style>
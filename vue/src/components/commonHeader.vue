<template>
      <div class="contain">
    <div class="blank"></div>
        <span><img class="user" :src="userImg" alt=""></span>
        <div class="name">小小</div>
        <div class="exit">
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
            collapseMenu:false
        }
    },
    methods:{
        handleMenu(){

        }
    }
};
</script>
<style lang="less">
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
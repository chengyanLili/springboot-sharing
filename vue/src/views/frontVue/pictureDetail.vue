<template>
  <div>
    <FrontHeader></FrontHeader>
    <div class="container">
      <!--      资源详情-->
      <el-card class="box-card">

        <el-image :src="sourInfo.url" alt=""></el-image>

      </el-card>
      <div class="items">
        <!--        点赞和收藏-->
        <div class="item" style="display: flex;justify-content: center">
          <!--        点赞-->
          <div>
            <div v-if="recordInfo.like1" @click="dianZan(userInfo.sourId)" style="color: #409EFF" class="dianZanIcon el-icon-thumb"></div>
            <div v-if="!recordInfo.like1" @click="dianZan(userInfo.sourId)" style="color: #0e0e0e" class="dianZanIcon el-icon-thumb"></div>
            <div class="dianZan">点赞</div>
          </div>
          <!--        收藏-->
          <div style="margin-left: 30px">
            <div v-if="recordInfo.favor" @click="favor(userInfo.sourId)" style="color: #409EFF;" class="dianZanIcon el-icon-star-off"></div>
            <div v-if="!recordInfo.favor" @click="favor(userInfo.sourId)" style="color: #0e0e0e" class="dianZanIcon el-icon-star-off"></div>
            <div class="dianZan">收藏</div>
          </div>
        </div>
        <!--        点击下载-->
        <div class="download">
          <button class="text" @click="download(sourInfo.url)">点击下载</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import FrontHeader from "@/components/frontHeader";
import request from "@/utils/request";

export default {
  name: "Details",
  components:{
    FrontHeader,
  },
  data(){
    return{
      userInfo:[],
      sourInfo:[],
      recordInfo:[]
    }
  },
  created() {
    let userInfo = sessionStorage.getItem('user')
    this.userInfo = JSON.parse(userInfo)
//获取会话储存中的资源信息
    let sourInfo = sessionStorage.getItem('sourInfoSession')
    this.sourInfo = JSON.parse(sourInfo)
    //初始化记录表，获取初始的点赞和收藏状态
    this.getRecord()
    console.log('@@@',this.sourInfo.url)
    request.get("http://localhost:9090/file/sourId?sourId=" + sourId.sourId ).then(res =>{
      console.log('试试',res)
    })
    this.getRecord()
  },
  methods:{
    dianZan(sourId){
      console.log(this.userInfo.userId)
      console.log('@',this.sourInfo.sourId)
      request.post("http://localhost:9090/record?userId="+this.userInfo.userId + "&sourId=" + this.sourInfo.sourId + "&type=1"+"&judge=" + !this.recordInfo.like1)
      //重新获取展示数据
      location.reload()
    },
    favor(sourId){
      console.log('@',this.recordInfo.favor)
      request.post("http://localhost:9090/record?userId="+this.userInfo.userId + "&sourId=" + this.sourInfo.sourId + "&type=2"+"&judge=" + !this.recordInfo.favor)
      location.reload()
    },
    getRecord(){
      console.log('hhh',this.sourInfo.sourId)
      request.get("http://localhost:9090/record?userId="+this.userInfo.userId +"&sourId=" +this.sourInfo.sourId).then((res)=>{
        this.recordInfo = res
      }).catch(err => {
      })
    },
    download(url) {
      window.open(url)
    }
  }
}
</script>

<style scoped>
.items{
display: flex;
  justify-content: center;
  align-items: center;
  margin: 20px 30% 0 0;
}
.download{
  margin-left: 40px;
}
.container{
  width: 70%;
  margin: 10px 25%;
}
.text {
  font-size: 14px;
}


.box-card {
  width: 70%;
}

</style>
<template>
<div>
  <FrontHeader></FrontHeader>
  <div class="swiper">
    <template>
      <el-carousel :interval="4000" type="card" height="200px">
        <el-carousel-item v-for="item in 6" :key="item">
          <h3 class="medium">{{ item }}</h3>
        </el-carousel-item>
      </el-carousel>
    </template>
  </div>
<div class="search" style=" margin: 10px 10%;">
  <el-input style="width: 200px;margin: 0 10px 10px 0" suffix-icon="el-icon-search" v-model="titleText" placeholder="请输入名称"></el-input>
  <el-button class="ml-5" type="primary" @click="searchSour">搜索</el-button>
</div>

  <sharing :videoMenu="videoMenu" :pictureMenu="pictureMenu" :documentMenu="documentMenu"></sharing>
</div>
</template>

<script>
import FrontHeader from "@/components/frontHeader";
import sharing from "@/components/sharing";
import request from "@/utils/request";

export default {
  name: "sourceShare",
  components:{
    FrontHeader,
    sharing
  },
  data(){
    return{
      videoMenu:[],
      pictureMenu:[],
      documentMenu:[],
      titleText:'',
      userInfo:[]
    }
  },
  created() {
    let userInfo = sessionStorage.getItem('user')
    this.userInfo=JSON.parse(userInfo)
    console.log(this.userInfo,'kk')
    request.post("http://localhost:9090/file/calculate",this.userInfo).then(res =>{
      console.log('file',res)
      this.videoMenu = res.data.filter(item=> item.type === 'mp4' )
      this.pictureMenu = res.data.filter(item=> item.type === 'jpeg' |item.type === 'jpg'|item.type === 'png' |item.type === 'gif')
      this.documentMenu = res.data.filter(item=> item.type === 'txt' | item.type == 'docx' )
    })
  },
  methods:{
    searchSour(){
      request("http://localhost:9090/file/search?searchText="+this.titleText).then((res) => {
        console.log(res,'搜索返回的资源')
        this.videoMenu = res.filter(item=> item.type === 'mp4' )
        this.pictureMenu = res.filter(item=> item.type === 'jpeg' |item.type === 'jpg'|item.type === 'png' |item.type === 'gif')
        this.documentMenu = res.filter(item=> item.type === 'txt' | item.type == 'docx' )
      })
    }
  }
}
</script>
<style scoped>
.swiper{
  margin: 10px 10%;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

</style>
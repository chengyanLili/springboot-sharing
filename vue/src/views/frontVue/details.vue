<template>
<div>
  <FrontHeader></FrontHeader>
 <div class="container">
  <video-player class="video-player vjs-custom-skin"
       ref="videoPlayer"
       :playsinline="true"
       :options="playerOptions"
  ></video-player>
   <!--        点赞和收藏-->
   <div style="display: flex;justify-content: center;margin-top: 20px">
     <!--        点赞-->
     <div>
       <div v-if="recordInfo.like1" @click="dianZan(userInfo.sourId)" style="color:  #409EFF;font-size: 18px" class="dianZanIcon el-icon-thumb"></div>
       <div v-if="!recordInfo.like1" @click="dianZan(userInfo.sourId)" style="color: #0e0e0e;font-size: 18px" class="dianZanIcon el-icon-thumb"></div>
       <div class="dianZan">点赞</div>
     </div>
     <!--        收藏-->
     <div style="margin-left: 30px">
       <div v-if="recordInfo.favor" @click="favor(userInfo.sourId)" style="color: #409EFF;font-size: 18px" class="dianZanIcon el-icon-star-off"></div>
       <div v-if="!recordInfo.favor" @click="favor(userInfo.sourId)" style="color: #0e0e0e;font-size: 18px" class="dianZanIcon el-icon-star-off"></div>
       <div class="dianZan">收藏</div>
     </div>
   </div>
 </div>
</div>
</template>

<script>
import FrontHeader from "@/components/frontHeader";
import {videoPlayer} from "vue-video-player";
import 'video.js/dist/video-js.css'
import request from "@/utils/request";

export default {
  //注入页面刷新依赖
  name: "Details",
  components:{
    FrontHeader,
    videoPlayer
  },
  data(){
    return{
      playerOptions: {
        playbackRates: [0.5, 1.0, 1.5, 2.0], // 可选的播放速度
        autoplay: false, // 如果为true,浏览器准备好时开始回放。
        muted: false, // 默认情况下将会消除任何音频。
        loop: false, // 是否视频一结束就重新开始。
        preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: 'zh-CN',
        aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        sources: [{
          type: "video/mp4", // 类型
          src: 'http://localhost:9090/file/e3999407ed024976a88cefebbeff5328.mp4' // url地址
        }],
        poster: '', // 封面地址
        notSupportedMessage: '此视频暂无法播放，请稍后再试', // 允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
          timeDivider: true, // 当前时间和持续时间的分隔符
          durationDisplay: true, // 显示持续时间
          remainingTimeDisplay: true, // 是否显示剩余时间功能
          fullscreenToggle: true // 是否显示全屏按钮
        }
    },
      userInfo:[],
      sourInfo:[],
      recordInfo:[],
      isThumb:false,
      isFavor:false
  }
  },
  created() {
    let userInfo = sessionStorage.getItem('user')
    this.userInfo = JSON.parse(userInfo)
    //从路由跳转中获取信息，页面一刷新数据就消失了，不能实现点赞功能
    // let sourId = this.$route.query.sourId
    // this.sourInfo = sourId

    //获取会话储存中的资源信息
    let sourInfo = sessionStorage.getItem('sourInfoSession')
    this.sourInfo = JSON.parse(sourInfo)

    //播放器里面给src赋值
    request.get("http://localhost:9090/file/sourId?sourId=" + this.sourInfo.sourId ).then(res =>{
      this.playerOptions.sources[0].src = res.url
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
        console.log('请求到的record记录',res)
      }).catch(err => {
        console.log('请求record错误',err)
      })
    }
  },

}
</script>

<style scoped>
.container{
  width: 80%;
  margin: 10px 10%;
}

</style>
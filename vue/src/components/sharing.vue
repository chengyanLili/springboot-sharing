<template>
  <div class="container">
    <!--      视频资源-->
    <div class="type">视频资源</div>
    <div class="line"></div>
    <div class="items">
      <div class="box" v-for="(item,index) in videoMenu" :key="item.sourId">
        <div class="content">
          <div class="title">{{ item.title }}</div>
          <!--    浏览量，点赞量，收藏量，评论数-->
          <div class="icons">
            <div class="click">
              <div class="el-icon-video-play"></div>
              <div class="text">{{ item.clickNum }}</div>
            </div>
            <div class="thumb">
              <div class="el-icon-thumb"></div>
              <div class="text">{{ item.likeNum }}</div>
            </div>
            <div class="favor">
              <div class="el-icon-star-off"></div>
              <div class="text">{{ item.favorNum }}</div>
            </div>
            <div class="comment">
              <div class="el-icon-chat-line-round"></div>
              <div class="text">{{ item.commentNum }}</div>
            </div>
            <div class="time">
              <div class="el-icon-time"></div>
              <div class="text">{{ item.submitTime }}</div>
            </div>
          </div>
        </div>
        <el-card class="videoItem" style="display: flex;justify-content: space-between">
          <span @click="toDetail(item)" >{{item.title}}</span>
        </el-card>
      </div>
    </div>

    <!--    课件资源-->
    <div class="type">课件资源</div>
    <div class="line"></div>

    <div class="items">
      <!--      课件资源-->
      <div class="box" v-for="(item,index) in documentMenu" :key="item.sourId">
        <div class="content">
          <div class="title">{{ item.title }}</div>
          <!--    浏览量，点赞量，收藏量，评论数-->
          <div class="icons">
            <div class="click">
              <div class="el-icon-video-play"></div>
              <div class="text">{{ item.clickNum }}</div>
            </div>
            <div class="thumb">
              <div class="el-icon-thumb"></div>
              <div class="text">{{ item.likeNum }}</div>
            </div>
            <div class="favor">
              <div class="el-icon-star-off"></div>
              <div class="text">{{ item.favorNum }}</div>
            </div>
            <div class="comment">
              <div class="el-icon-chat-line-round"></div>
              <div class="text">{{ item.commentNum }}</div>
            </div>
            <div class="time">
              <div class="el-icon-time"></div>
              <div class="text">{{ item.submitTime }}</div>
            </div>
          </div>
        </div>
        <div class="grid-content bg-purple">
          <div class="documentItem">
            <el-card class="videoItem" style="display: flex;justify-content: space-between">
              <span @click="toCurseDetail(item)" >{{item.title}}</span>
            </el-card>
          </div>
        </div>
      </div>

    </div>
    <div class="type">图片资源</div>
    <div class="line"></div>
    <div class="items">
<!--      图片资源-->

    <div class="box" v-for="(item,index) in pictureMenu" :key="item.sourId">
        <div class="content">
          <div class="title">{{ item.title }}</div>
          <!--    浏览量，点赞量，收藏量，评论数-->
          <div class="icons">
            <div class="click" >
              <div class="el-icon-video-play"></div>
              <div class="text">{{ item.clickNum }}</div>
            </div>
            <div class="thumb">
              <div class="el-icon-thumb"></div>
              <div class="text">{{ item.likeNum }}</div>
            </div>
            <div class="favor">
              <div class="el-icon-star-off"></div>
              <div class="text">{{ item.favorNum }}</div>
            </div>
            <div class="comment">
              <div class="el-icon-chat-line-round"></div>
              <div class="text">{{ item.commentNum }}</div>
            </div>
            <div class="time">
              <div class="el-icon-time"></div>
              <div class="text">{{ item.submitTime }}</div>
            </div>
          </div>
        </div>
      <el-card class="videoItem" style="display: flex;justify-content: space-between">
        <span @click="toPictureDetail(item)" >{{item.title}}</span>
      </el-card>
    </div>
    </div>

  </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: "sharing",
  data(){
    return{
      userInfo:[],
      isThumb:false,
      isFavor:false,
      thumbList:[],
      favorList:[]
    }
  },
  props:['videoMenu','pictureMenu','documentMenu'],
  created() {
    //获取用户的缓存信息
    let userInfo = sessionStorage.getItem('user')
    this.userInfo = JSON.parse(userInfo)
    // console.log('课件',this.documentMenu)
    // console.log('视频',this.videoMenu)
    // console.log('文档',this.pictureMenu)
  },
  methods:{
    dianZan(sourId){
      console.log(this.userInfo.userId)
      request.post("http://localhost:9090/record?userId="+this.userInfo.userId + "&sourId=" + sourId + "&type=1"+"&judge=" + this.isThumb)
      this.isThumb = !this.isThumb
    },
    favor(sourId){
      console.log(this.userInfo.userId)
      request.post("http://localhost:9090/record?userId="+this.userInfo.userId + "&sourId=" + sourId + "&type=2"+"&judge=" + this.isFavor)
      this.isFavor = !this.isFavor
    },
    toDetail(sourId){
      console.log('sourId',sourId)
      sessionStorage.setItem('sourInfoSession',JSON.stringify(sourId))
      this.$router.push({path: "/details", query: {sourId: sourId}})
    //  点击量的增加
      request.post("http://localhost:9090/file/clickNum?sourId=" +sourId.sourId)
    },
    toCurseDetail(sourId){
      sessionStorage.setItem('sourInfoSession',JSON.stringify(sourId))
      console.log('传给课件详情',sourId)
      this.$router.push({path: "/curseDetail", query: {sourId: sourId}})
      //  点击量的增加
      request.post("http://localhost:9090/file/clickNum?sourId=" +sourId.sourId)
    },
    toPictureDetail(sourId){
      sessionStorage.setItem('sourInfoSession',JSON.stringify(sourId))
      console.log('传给课件详情',sourId)
      this.$router.push({path: "/pictureDetail", query: {sourId: sourId}})
      //  点击量的增加
      request.post("http://localhost:9090/file/clickNum?sourId=" +sourId.sourId)
    },
    download(url) {
      window.open(url)
    }
  }
}
</script>

<style lang="less" scoped>
img{
  width: 100%;
  height: 100%;
}
.container {
  width: 80%;
  margin: 10px 10%;
  .items{
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
    .box{
      width: 49%;
      margin-top:15px ;
    }
  }
  .line{
    margin: 0 10% 5px 10%;
    width: 65px;
    height: 2px;
    background-color: #7cbd7c;
  }
  .type{
    margin: 50px 10% 5px 10%;
  }
  .pictureItem{
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 250px;
    background-color: #f1efe8;
    border-radius: 15px;
  }
  .videoItem{
    cursor:pointer;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-radius: 15px;
  }
  .videoItem:hover{
    color: #6377e3;
  }
  .documentItem{
    justify-content: center;
    align-items: center;
    width: 100%;
    border-radius: 15px;
  }
  .title{
    margin-bottom: 5px;
    margin-left: 10px;
  }
  .thumb{
    color: #a1a0a0;
    font-size: 14px;
    display: flex;
    align-items: center;
    margin: 0 10px 5px 5px;
    .el-icon-thumb{
      font-size: 18px;
    }
    .text{
      margin-left: 5px;
    }
  }
  .time{
    color: #a1a0a0;
    font-size: 14px;
    display: flex;
    align-items: center;
    margin: 0 10px 5px 5px;
    .el-icon-time{
      font-size: 18px;
    }
    .text{
      margin-left: 5px;
    }
  }
  .comment{
    color: #a1a0a0;
    font-size: 14px;
    display: flex;
    align-items: center;
    margin: 0 10px 5px 5px;
    .el-icon-chat-line-round{
      font-size: 18px;
    }
    .text{
      margin-left: 5px;
    }
  }
  .favor{
    color: #a1a0a0;
    font-size: 14px;
    display: flex;
    align-items: center;
    margin: 0 10px 5px 5px;
    .el-icon-star-off{
      font-size: 18px;
    }
    .text{
      margin-left: 5px;
    }
  }
  .icons{
    display: flex;
    align-items: center;

    .click{
      color: #a1a0a0;
      font-size: 14px;
      display: flex;
      align-items: center;
      margin: 0 15px 5px 15px;
      .el-icon-video-play{
        font-size: 18px;
      }
      .text{
        margin-left: 5px;
      }
    }
  }
  }

.el-row {
  margin-bottom: 20px;

  &:last-child {
    margin-bottom: 0;
  }
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
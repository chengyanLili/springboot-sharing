<template>
  <div class="container">
    <div class="items">
    <div class="box" v-for="(item,index) in videoMenu" :key="index">
        <div class="content">
          <div class="title">{{ item.title }}</div>
          <!--    浏览量，点赞量，收藏量，评论数-->
          <div class="icons">
            <div class="click">
              <div class="el-icon-video-play"></div>
              <div class="text">{{ item.click }}</div>
            </div>
            <div class="thumb">
              <div class="el-icon-thumb"></div>
              <div class="text">{{ item.lickNum }}</div>
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
          <div class="item">

视频
          </div>
        </div>
    </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "sharing",
  data(){
    return{

    }
  },
  props:['videoMenu'],
  created() {
    console.log(this.videoMenu)
  }
}
</script>

<style lang="less" scoped>
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
  .item{

    justify-content: center;
    align-items: center;
    width: 100%;
    height: 250px;
    background-color: #f1efe8;
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
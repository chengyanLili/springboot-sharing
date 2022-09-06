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

  <div class="type">视频资源</div>
  <div class="line"></div>
  <sharing :videoMenu="videoMenu"></sharing>
</div>
</template>

<script>
import FrontHeader from "@/components/frontHeader";
import sharing from "@/components/sharing";

export default {
  name: "sourceShare",
  components:{
    FrontHeader,
    sharing
  },
  data(){
    return{
      videoMenu:[
        {
          title:'晴天【周杰伦】',
          author:'小恐龙',
          submitTime:'2022-9-4',
          click:57,
          lickNum:10,
          favorNum:2078,
          commentNum:1,
        },{
          title:'七里香【周杰伦】',
          author:'小恐龙',
          submitTime:'2022-9-4',
          click:89,
          lickNum:10908,
          favorNum:20,
          commentNum:3
        },{
          title:'哈哈哈【周杰伦】',
          author:'小恐龙',
          click:43,
          submitTime:'2022-9-4',
          lickNum:101,
          favorNum:2097,
          commentNum:19
        },{
          title:'苹果【周杰伦】',
          author:'小恐龙',
          submitTime:'2022-9-4',
          click:90,
          lickNum:11,
          favorNum:28,
          commentNum:19
        },{
          title:'橘子【周杰伦】',
          author:'小恐龙',
          click:678,
          submitTime:'2022-9-4',
          lickNum:120,
          favorNum:178,
          commentNum:16
        },{
          title:'java【周杰伦】',
          author:'小恐龙',
          click:356,
          submitTime:'2022-9-4',
          lickNum:130,
          favorNum:578,
          commentNum:16
        }
      ],
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
.line{
  margin: 0 10% 5px 10%;
  width: 65px;
  height: 2px;
  background-color: #7cbd7c;
}
.type{
  margin: 0 10% 5px 10%;
}
</style>
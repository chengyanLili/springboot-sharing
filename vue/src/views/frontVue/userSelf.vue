<template>
  <div>
      <FrontHeader></FrontHeader>
    <div class="publish">
      <div class="box1">
        <div class="title">标题 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <el-input style="width: 250px;" v-model="titleInput" placeholder="请输入内容"></el-input>
      </div>
      <div class="box1">
        <div class="title">资源简介</div>
        <textarea id="message" name="message"　cols="50" rows="10" placeholder="请输入资源简介"></textarea>
      </div>
      <div class="box1">
        <div class="title">点击上传</div>
        <el-upload
            class="avatar-uploader"
            action="https://jsonplaceholder.typicode.com/posts/"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </div>
      <div class="btn" style="margin-left: 10%;margin-top: 10px">
        <el-button type="primary" plain @click="publish">发布</el-button>
        <span style="margin-left: 10px;color: #bac2cb;font-size: 13px ">状态:{{state}}</span>
      </div>
       </div>
    <div class="state">
      <div class="ready">
        <div>审核中</div>
        <el-table
            :data="readyData"
            style="width: 100%">
          <el-table-column
              prop="title"
              label="标题"
             >
          </el-table-column>
          <el-table-column
              prop="brief"
              label="资源简介"
              >
          </el-table-column>
          <el-table-column
              prop="time"
              label="上传时间">
          </el-table-column>
        </el-table>
      </div>
      <div class="already">
        <div>已发布</div>
        <el-table
            :data="readyData"
            style="width: 100%">
          <el-table-column
              prop="title"
              label="标题"
          >
          </el-table-column>
          <el-table-column
              prop="brief"
              label="资源简介"
          >
          </el-table-column>
          <el-table-column
              prop="time"
              label="上传时间">
          </el-table-column>
        </el-table>
      </div>
    </div>

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
      imageUrl: '',
      titleInput:'',
      briefInput:'',
      state:'审核中',
      readyData:[{
        title:'哈哈哈',
        brief:'哈哈哈哈哈',
        time:'2675-37-33'
      }]
    };
  },
  methods:{
    clickMenu(item){
      this.$router.push({
        name:item.name
      })
    },
    publish(){
      this.$message({
        showClose: true,
        message: '已上传，等待管理员审核'
      });
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    }
  }

}
</script>

<style lang="less" scoped>
.state{
  margin: 20px 20%;
}
.already{
  margin-top: 10px;
}
.publish{
  margin: 0 30%;
}
.box1{
display: flex;
  align-items: center;
  margin-top: 10px;
  .title{
    margin: 20px 20px;
  }
}
.avatar-uploader{
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}

</style>
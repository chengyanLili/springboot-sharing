<template>
  <div>
      <FrontHeader></FrontHeader>
    <div class="publish">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="文件标题">
          <el-input v-model="form.title"></el-input>
        </el-form-item>
        <el-form-item label="文件简介">
          <el-input type="textarea" v-model="form.brief"></el-input>
        </el-form-item>
        <el-form-item label="文件标签">
          <el-select v-model="value1" multiple multiple-limit="3" style="width: 400px; margin: 3px auto" placeholder="请选择三个感兴趣的领域">
            <el-option
                v-for="item in options"
                :key="item.labelId"
                :label="item.name"
                :value="item.labelId">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
       <div class="box1">
        <div class="title">文件上传</div>
        <el-upload
            class="avatar-uploader"
            action=""
            :http-request="changeFile"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
          <el-button>上传文件</el-button>
        </el-upload>
      </div>
      </div>

    <div class="state">
      <div class="ready">
        <div>我上传的资源</div>
        <el-table
            align="center"
            border
            :data="sourceList"
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
              fixed="right"
              label="状态">
            <template slot-scope="scope">
              <el-button v-if="scope.row.state == 0" type="warning" size="small">审核中</el-button>
              <el-button v-if="scope.row.state == 1" type="primary" size="small">已发布</el-button>
              <el-button v-if="scope.row.state == 2" type="danger" size="small">已退回</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import FrontHeader from "@/components/frontHeader";
import request from "@/utils/request";
export default {
  name: "userSelf",
  components:{
    FrontHeader,
  },
  data() {
    return {
      imageUrl: '',
      options:[],
      value1:[],
      titleInput:'',
      briefInput:'',
     sourceList:[],
      form: {
        title: '',
        brief: '',
      },
      userInfo:[]
    };
  },
  created() {
    let userInfo = sessionStorage.getItem('user')
    this.userInfo=JSON.parse(userInfo)
    this.load(),
        this.getSourceList()
  }
  ,
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
      console.log('shsh')
    },
    beforeAvatarUpload(file) {

    },
    load() {
      request.get("http://localhost:9090/label").then(res =>{
        this.options = res
      })
    },

    //根据用户id查询资源信息
    getSourceList(){
    request("http://localhost:9090/file/user/" + this.userInfo.userId).then(res =>{
      this.sourceList = res
    })
    },

  changeFile(file){
      let fd = new FormData()
    fd.append('file',file.file)
    fd.append('title',this.form.title)
    fd.append('brief',this.form.brief)
    fd.append('labelId1',this.value1[0])
    fd.append('labelId2',this.value1[1])
    fd.append('labelId3',this.value1[2])
    fd.append('userId',this.userInfo.userId)
    request.post("http://localhost:9090/file/upload",fd).then((res)=>{
      console.log('成功',res)
        }
    ).catch((err)=>{
      this.$message('上传成功，等待管理员审核')
      console.log(err)
    })
  }
  }

}
</script>

<style lang="less" scoped>
.state{
  margin: 20px 10%;
}
.already{
  margin-top: 10px;
}
.publish{
  margin: 20px 30%;
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
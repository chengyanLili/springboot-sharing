<template>
  <div>
    <div class="search">
      <el-input style="width: 200px;margin: 0 10px 10px 0" v-model="title" placeholder="请输入名称"></el-input>
      <el-button @click="load" type="primary" style="height: 38px">搜索</el-button>
    </div>
    <el-card class="box-card">
      <el-table
          :data="tableData"
          border
          style="width: 100%"
          height="500px"
          max-height="500px">
        <el-table-column
            prop="sourId"
            label="文件id">
        </el-table-column>
        <el-table-column
            prop="title"
            label="文件名称">
        </el-table-column>
        <el-table-column
            prop="brief"
            label="文件主题">
        </el-table-column>
        <el-table-column
            prop="type"
            label="文件类型">
        </el-table-column>
        <el-table-column
            prop="size"
            label="文件大小(kb)">
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
        <el-table-column
            fixed="right"
            label="操作">
          <template slot-scope="scope">
            <el-button slot="reference" @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
            <el-button style="margin-left: 10px" type="text" size="small" @click="check(scope.row.sourId)">审核</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog
          title="审核"
          :visible.sync="centerDialogVisible"
          width="30%"
          center>
        <span slot="footer" class="dialog-footer">
    <el-button @click="noPass()">退 回</el-button>
    <el-button type="primary" @click="pass()">通 过</el-button>
  </span>
      </el-dialog>
      <!--   分页 -->

      <el-pagination
          style="padding-top: 15px"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </el-card>
  </div>
</template>


<script>
import request from "@/utils/request";

export default {
  name: 'sourceManage',
  data () {
    return {
      currentPage4: 4,
      value: '',
      tableData: [],
      title:'',
      total: 0,
      pageNum: 1,
      pageSize: 5,
      username: "",
      input:'',
      centerDialogVisible:false,
      sourId:[],
      submitTime:''
    }
  },
  created() {
    //请求数据
    this.sourInfo()
  //  请求分页的数据
    this.load()
  },
  methods: {
    //分页查询
    load(){
      request("http://localhost:9090/file/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&title="+this.title)
          .then(res => {
            console.log('分页',res)
            this.total = res.total
            this.tableData = res.data
          })
    },
    sourInfo(){
      request.get("http://localhost:9090/file").then(res =>{
        console.log('file',res)
        this.tableData = res
      })
    },
    handleDelete(row) {
      console.log(row);
      request.delete("http://localhost:9090/file/"+ row.sourId).then(res => {
        console.log(res)
        this.$message({
          type: "success",
          message: "删除成功"
        })
        this.sourInfo()
      })
    },
    pass(){
      //获取系统时间
      this.getNowDate()
      console.log(this.submitTime,'hhhh')
      request.post("http://localhost:9090/file/check?checkRes=1"+"&sourId=" +this.sourId + "&submitTime=" + this.submitTime).then(()=>{
        this.sourInfo()
        //页面刷新
        location.reload()
        this.$message('审核通过')
      }).catch(err=>{

      })
      this.centerDialogVisible=false
    },
    noPass(){
      this.getNowDate()
      console.log(this.submitTime,'hhhh')
      request.post("http://localhost:9090/file/check?checkRes=2"+"&sourId=" +this.sourId + "&submitTime=" + this.submitTime).then(()=>{
       this.sourInfo()

        //页面刷新
        location.reload()
        this.message('已退回')
      })
      this.centerDialogVisible=false
    },
    check(sourId){
      this.sourId = sourId
      this.centerDialogVisible=true
    },
    getNowDate() {
      const timeOne = new Date()
      const year = timeOne.getFullYear()
      let month = timeOne.getMonth() + 1
      let day = timeOne.getDate()
      let submitTime = `${year}-${month}-${day}`
      this.submitTime =submitTime
    },

    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    download(url){
      window.open(url)
    }
    }


}

</script>


<style scoped>
.search{
  display: flex;
}
</style>
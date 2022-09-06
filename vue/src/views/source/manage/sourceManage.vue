<template>
  <div>
    <div class="search">
      <el-input style="width: 200px;margin: 0 10px 10px 0" v-model="input" placeholder="请输入姓名"></el-input>
      <el-button type="primary" style="height: 38px">搜索</el-button>
    </div>
    <el-card class="box-card">
      <el-table
          :data="tableData"
          border
          style="width: 100%; height: 400px">
        <el-table-column
            prop="name"
            label="资源名称">
        </el-table-column>
        <el-table-column
            prop="title"
            label="资源主题">
        </el-table-column>
        <el-table-column
            prop="url"
            label="url地址">
        </el-table-column>
        <el-table-column
            fixed="right"
            label="状态">
          <template slot-scope="scope">
            <el-button v-if="scope.row.state == 0" type="warning" size="small">审核中</el-button>
            <el-button v-if="scope.row.state == 1" type="primary" size="small">已发布</el-button>
          </template>
        </el-table-column>
        <el-table-column
            fixed="right"
            label="操作">
          <template slot-scope="scope">
            <el-popconfirm
                title="这是一段内容确定删除吗？"
            >
            <el-button slot="reference" @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
            </el-popconfirm>
              <el-button style="margin-left: 20px" type="text" size="small">设为管理员</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--   分页 -->
      <el-pagination
          style="padding-top: 15px"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage4"
          :page-sizes="[100, 200, 300, 400]"
          :page-size="100"
          layout="total, sizes, prev, pager, next, jumper"
          :total="400">
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
      formInline: {
        user: '',
        email: '',
        city: '',
        radio: '1',
        nikeName: '',
      },
      currentPage4: 4,
      value: '',
      tableData: [
        {
          name:'尴尬',
          title:'哈哈',
          state:0
        },
        {
          name:'略略略',
          title:'呃呃',
          state:1
        }
      ],
      input:''
    }
  },
  created() {
    //请求数据
    request.get("http://localhost:9090/user").then(res =>{
      let userArr = res
      userArr.forEach(item =>{
        if(item.identify == 0){
          item.identify = "管理员"
          // console.log('管理员')
        }else{
          item.identify = "普通用户"
          // console.log('普通用户')
        }
      })
      this.tableData = userArr
    })
  },
  methods: {
    handleDelete(row) {
      console.log(row);
    },
    onSubmit () {
      console.log('submit!')
    },
    handleSizeChange (val) {
      console.log(`每页 ${val} 条`)
    },
    handleCurrentChange (val) {
      console.log(`当前页: ${val}`)
    }
  }
}

</script>


<style scoped>
.search{
  display: flex;
}
</style>
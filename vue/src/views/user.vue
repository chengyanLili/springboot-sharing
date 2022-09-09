<template>
    <div>
        <el-input style="width: 200px;margin: 0 10px 10px 0" suffix-icon="el-icon-search" v-model="username" placeholder="请输入姓名"></el-input>
        <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
        <!-- 用户列表卡片 -->
        <el-card class="box-card">
            <el-table
                height="500px"
                max-height="500px"
                    :data="tableData"
                    border
                    style="width: 100%; height: 400px">
                <el-table-column
                        prop="username"
                        label="用户名">
                </el-table-column>
                <el-table-column
                        prop="identify"
                        label="是否为管理员">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作">
                    <template slot-scope="scope">
                        <el-button @click="handleClick(scope.row)" type="text" size="small">删除</el-button>
                        <el-button @click="handleClick1(scope.row)" type="text" size="small">设为管理员</el-button>
                    </template>
                </el-table-column>
            </el-table>
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
        name: 'Users',
        data () {
            return {
                currentPage4: 4,
                value: '',
                tableData: [],
                total: 0,
                pageNum: 1,
                pageSize: 5,
                username: ""
            }
        },
        created() {
                //请求分页查询数据
                this.load()
        },


        methods: {

          handleClick1(row) {
            // console.log(row);
            request.post("http://localhost:9090/user/manager?userId="+ row.userId).then(res => {
              this.$message({
                type: "success",
                message: "设为管理员成功"
              })
              this.load()
            })
          },
          handleClick(row) {
            // console.log(row);
            request.delete("http://localhost:9090/user/"+ row.userId).then(res => {
              console.log(res)
              this.$message({
                type: "success",
                message: "删除成功"
              })
              this.load()
            })
          },
            load() {
                request("http://localhost:9090/user/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&username="+this.username)
                    .then(res => {
                      let userArr = res.data
                      userArr.forEach(item =>{
                        if(item.identify == 0){
                          item.identify = "管理员"
                        }else{
                          item.identify = "普通用户"
                        }
                      })
                        this.tableData = userArr
                        this.total = res.total
                    })
            },
            handleSizeChange (pageSize) {
                console.log(`每页 ${pageSize} 条`)
                this.pageSize = pageSize
                this.load()
            },
            handleCurrentChange (pageNum) {
                console.log(`当前页: ${pageNum}`)
                this.pageNum = pageNum
                this.load()
            }
        }
    }

</script>


<style scoped>

</style>
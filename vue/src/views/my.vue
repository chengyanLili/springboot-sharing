<template>
  <div>
    <div class="state">
      <div class="ready">
        <div>待审核</div>
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
export default {
  name: "my"
}
</script>

<style scoped>
.state{
  margin:10px;
}
.already{
  margin-top: 10px;
}
</style>
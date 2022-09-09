<template>
  <div style="width: 100%; height: 100vh; overflow: hidden">
    <el-card style="width: 400px; margin: 150px auto">
      <div style="font-size: 30px; text-align: center; padding: 30px 0">欢迎注册</div>
        <el-form ref="form" :model="form" size="normal" :rules="rules">
          <el-form-item prop="username">
            <el-input prefix-icon="el-icon-user-solid" v-model="form.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" v-model="form.password" show-password placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item prop="confirm">
            <el-input prefix-icon="el-icon-lock" v-model="form.confirm" show-password placeholder="请确认密码"></el-input>
          </el-form-item>
          <el-select v-model="value1" multiple multiple-limit="3" style="width: 400px; margin: 3px auto" placeholder="请选择三个感兴趣的领域">
            <el-option
                    v-for="item in options"
                    :key="item.labelId"
                    :label="item.name"
                    :value="item.labelId">
            </el-option>
          </el-select>
          <el-form-item>
            <a style="float: right;margin-right: 20px" href="/login">已有账号？请登录</a>
          </el-form-item>
          <el-form-item>
            <el-button style="width: 100%; margin: 20px auto" type="primary" @click="register">注 册</el-button>
          </el-form-item>
        </el-form>
    </el-card>


  </div>

</template>

<script>
import request from "@/utils/request";

export default {
  name: "Register",
  data() {
    return {
      options: [],
      value1: [],
      form: {},
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
        confirm: [
          { required: true, message: '请确认密码', trigger: 'blur' },
        ],
      }
    }
  },
  created() {
    this.load()
  },
  methods:{
    register() {
      if (this.form.password !== this.form.confirm) {
        this.$message({
          type: "error",
          message: '2次密码输入不一致！'
        })
        return
      }
      this.$refs['form'].validate((valid) => {
        if (valid) {
          request.post("http://localhost:9090/user/register?username="+this.form.username+"&password="+this.form.password+"&labelId1="
                  +this.value1[0]+"&labelId2="+this.value1[1]+"&labelId3="+this.value1[2]).then(res => {
            console.log(res)
            if (res.code === '0'){
              this.$message({
                type: "success",
                message: "注册成功"
              })
              this.$router.push("/login")  //登录成功之后进行页面的跳转，跳转到主页
            }else {
              this.$message({
                type: "error",
                message: res.msg
              })
            }
          })
        }
      })

    },
    load() {
      request.get("http://localhost:9090/label").then(res =>{
        this.options = res
      })
    }

  }
}
</script>

<style scoped>

</style>
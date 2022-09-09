<template>
  <div style="width: 100%; height: 100vh; overflow: hidden;">
    <el-card style="width: 400px; margin: 150px auto">
      <div style="color: #030d2a; font-size: 30px; text-align: center; padding: 30px 0">欢迎登录</div>
        <el-form ref="form" :model="form" size="normal" :rules="rules">
          <el-form-item prop="username">
            <el-input prefix-icon="el-icon-user-solid" v-model="form.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" v-model="form.password" show-password placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-select v-model="value1" placeholder="请选择身份">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
          <el-form-item>
            <a style="float: right;margin-right: 20px" href="/register">还没有账号？请注册</a>
          </el-form-item>
          <el-form-item>
            <el-button style="width: 100%" type="primary" @click="login">登 录</el-button>
          </el-form-item>
        </el-form>
    </el-card>
  </div>
</template>
<script>
import request from "@/utils/request";

export default {
  name: "Login",
  data() {
    return {
      value1:[],
      form: {},
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
        identify: [
          { required: true, message: '请选择身份', trigger: 'blur' },
        ],
      },
      options: [{
        value:0,
        label:'管理员',
      },
        {
          value:1,
          label:'普通用户',
        }]
    }
  },
  created() {
    sessionStorage.removeItem("user")
    this.getUserInfo()
  },
  methods:{
    login(){
      console.log(this.value1)
      this.$refs['form'].validate((valid) => {
        // console.log('j',this.form)
        if (valid) {
          request.post("http://localhost:9090/user/login?username="+this.form.username+"&password="+this.form.password + "&identify="+this.value1).then(res => {
            console.log(res)
            if (res.code === '0'){
              this.$message({
                type: "success",
                message: "登录成功"
              })
              sessionStorage.setItem("user",JSON.stringify(res.data)) // 缓存用户信息
              if(this.value1==0){
                this.$router.push("/user")  //登录成功之后进行页面的跳转，跳转到主页:管理员
              }else{
                this.$router.push("/sourceShare")  //登录成功之后进行页面的跳转，跳转到资源显示界面:普通用户
              }

            }else {
              console.log('失败')
              this.$message({
                type: "error",
                message: res.msg
              })
            }
          })
        }
      })
    },
    getUserInfo(){
      request.get("http://localhost:9090/user").then(res=>{
        console.log(res)
      })
    }
  }
}
</script>

<style scoped>

</style>
<template>
  <div class="login_box">
    <!-- 登录模块 -->
    <el-form
        ref="loginFormRef"
        :model="loginForm"
        :rules="loginFormRules"
        label-width="0px"
        class="login_form"
    >
      <!-- 用户名 -->
      <el-form-item class="name" prop="username">
        <el-input
            v-model="loginForm.username"
            prefix-icon="el-icon-user"
        ></el-input>
      </el-form-item>

      <!-- 密码 -->
      <el-form-item class="password" prop="password">
        <el-input show-password
            v-model="loginForm.password"
            prefix-icon="el-icon-key"
            type="password"
        ></el-input>
      </el-form-item>
      <br /><br />
      <!-- 按钮 -->
      <el-form-item label-width="0px" class="btns">
        <el-button type="primary" @click="login" round>登录</el-button>
        <el-button type="info" @click="resetLoginForm" round>重置</el-button>
      </el-form-item>
      <el-form-item class="toreg">
        <router-link to="register">没有账号？立即注册</router-link>
        <el-button @click="toRegister" style="display: inline-block;margin-left: 10px" type="mini">注册</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>

<script>
export default {
  data() {
    return {
      //登录表单的数据绑定对象
      loginForm: {
        username: "admin",
        password: "123456",
      },
      //表单验证规则对象
      loginFormRules: {
        //验证用户名是否合法
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          {
            min: 3,
            max: 10,
            message: "长度在 3 到 10 个字符",
            trigger: "blur",
          },
        ],
        //验证密码是否合法
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 16, message: "长度在6到16个字符", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    //重置登录表单
    resetLoginForm() {
      // console.log(this);
      this.$refs.loginFormRef.resetFields();
    },
    toRegister(){
      this.$router.push("/register")
    }
  },
};
</script>

<style lang="less" scoped>
.login_box {

  width: 400px;
  height: 350px;
  background-color: aliceblue;
  border-radius: 15px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  opacity: 0.9;
}
.goRegister{
  position: absolute;
  margin-top:250px;
  margin-left: 30px;
}
.login_form {
  margin-top: 30px;
  .name{
    padding: 10px 20px;
  }
  .password{
    padding: 0 20px;
  }
  .btns{
    margin-left: 30%;
  }
  .toreg{
    margin-left: 45%;
  }
}
</style>


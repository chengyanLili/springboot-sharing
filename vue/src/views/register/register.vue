<template>
  <el-dialog :before-close="handleClose" title="用户注册" :visible.sync="dialogFormVisible" center>
    <el-row type="flex" justify="center">
      <el-form
          ref="formData"
          :model="formData"
          :rules="rules"
          label-width="80px"

          @keyup.enter.native="register()"
      >
        <el-form-item prop="userName" label="用户名"
        ><el-input
            v-model="formData.userName"
            placeholder="请输入用户名"
            prefix-icon="el-icon-user"
            clearable
        ></el-input
        ></el-form-item>
        <el-form-item prop="password" label="密码"
        ><el-input
            v-model="formData.password"
            placeholder="请输入密码"
            type="password"
            prefix-icon="iconfont icon-3702mima"
            clearable
        ></el-input
        ></el-form-item>
        <el-form-item prop="cheackPassword" label="确认密码"
        ><el-input
            v-model="formData.cheackPassword"
            placeholder="再次输入密码"
            type="password"
            prefix-icon="iconfont icon-3702mima"
            clearable
        ></el-input
        ></el-form-item>
        <el-form-item>
          <el-button
              type="primary"
              @click="register('formData')"
              icon="el-icon-upload"
          >注册</el-button
          >
          <el-button @click="resetForm('formData')"
          >重置</el-button
          ></el-form-item
        >
        <span class="tologin">  <router-link to="login">已有账户？立即登录</router-link></span>
      </el-form>
    </el-row>
  </el-dialog>
</template>

<script>
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.formData.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      formData: {
        userName: "",
        password: "",
        cheackPassword: "",
      },
      rules: {
        userName: [
          { required: true, message: "用户名不能为空", trigger: "blur" },
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" },
        ],
        cheackPassword: [
          { required: true, validator: validatePass, trigger: "blur" },
        ],
      },
      registerForm: {
        username: "",
        password: "",
      },
      dialogTableVisible: true,
      dialogFormVisible: true,
    };
  },
  methods: {
    register(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$message({
            type: "success",
            message: "注册成功",
          });
          this.$router.push("/login");
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    handleClose(done){
      this.$confirm('确定退出注册吗？').then(() => {
        // function(done)，done 用于关闭 Dialog
        done();

        this.$router.push("/login")
      }).catch(() => {
        console.log("点击确定时触发");
      });
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>
<style>
.tologin{
  margin-left: 56%;
}
</style>
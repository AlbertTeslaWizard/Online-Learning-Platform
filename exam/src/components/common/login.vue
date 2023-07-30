<!-- 用户登录界面 -->
<template>
  <div class="login-wrap">
    <div class="ms-login">
      <div class="ms-title">通识选修课线上学习平台</div>
      <el-form :label-position="labelPosition" label-width="0px" :model="formLabelAlign" class="ms-content">
        <el-form-item>
          <span slot="label">
          <span style="color: black; font-size: 16px">用户名</span>
          </span>
          <el-input v-model.number="formLabelAlign.username">
            <template #prepend>
              <el-button icon="el-icon-user"></el-button>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <span slot="label">
          <span style="color: black; font-size: 16px">密码</span>
          </span>
          <el-input v-model="formLabelAlign.password" type='password'>
            <template #prepend>
              <el-button icon="el-icon-lock"></el-button>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <span slot="label">
          <span style="color: black; font-size: 16px">验证码</span>
          </span>
          <el-input v-model="formLabelAlign.verifycode">
            <template #prepend>
              <el-button icon="el-icon-goods"></el-button>
            </template>
          </el-input>
        </el-form-item>

        <div class="login1">
          <el-button type="primary" class="login" @click="login()">登录</el-button>
          <el-button type="success" class="register" @click="register()">注册</el-button>
        </div>
      </el-form>

    </div>
  </div>
</template>

<script>
import store from '@/vuex/store'
import {mapState} from 'vuex'
export default {
  store,
  name: "login",
  data() {
    return {
      role: 2,
      labelPosition: 'top',
      formLabelAlign: {
        username: '20199163',
        password: '123456',
        verifycode: '19770704'
      }
    }
  },
  methods: {
    //用户登录请求后台处理
    login() {
      console.log("登录操作执行-------");
      this.$axios({
        url: `/api/login`,
        method: 'post',
        data: {
          ...this.formLabelAlign
        }
      }).then(res=>{
        let resData = res.data.data
        if(resData != null && this.formLabelAlign.verifycode == '19770704') {
          switch(resData.role) {
            case "0":  //管理员
              this.$cookies.set("cname", resData.adminName)
              this.$cookies.set("cid", resData.adminId)
              this.$cookies.set("role", 0)
              this.$router.push({path: '/index' }) //跳转到首页
              break
            case "1": //教师
              this.$cookies.set("cname", resData.teacherName)
              this.$cookies.set("cid", resData.teacherId)
              this.$cookies.set("role", 1)
              this.$router.push({path: '/index' }) //跳转到教师用户
              break
            case "2": //学生
              this.$cookies.set("cname", resData.studentName)
              this.$cookies.set("cid", resData.studentId)
              this.$router.push({path: '/student'})
              break
          }
        }
        if(resData == null || this.formLabelAlign.verifycode != '19770704') {
          this.$message({
            showClose: true,
            type: 'error',
            message: '用户名、密码或验证码错误'
          })
        }
      })
    },
    clickTag(key) {
      this.role = key
    },
    register() {
      this.$router.push({path:'/register'})
    }
  },
  computed: mapState(["userInfo"]),
  mounted() {

  }
}
</script>

<style scoped>
.login-wrap {
  position: fixed;
  width: 100%;
  height: 100%;
  background: url("../../assets/img/sch.jpg");
  background-size: cover;
}

.ms-login {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 300px;
  margin: -240px 0 0 -160px;
  border-radius: 5px;
  background: rgba(255, 255, 255, 0.9);
  overflow: hidden;
}

.ms-content {
  padding: 30px 30px;
}

.login-btn button {
  width: 100%;
  height: 36px;
  margin-bottom: 10px;
}

.ms-title {
  width: 100%;
  line-height: 50px;
  text-align: center;
  font-size: 20px;
  color: black;
  border-bottom: 1px solid #ddd;
}

.login1 {
  text-align: center;
}

</style>

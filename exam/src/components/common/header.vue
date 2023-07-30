<!-- 顶部信息栏 -->
<template>
  <!--
  <header id="topbar">
    <el-row>
      <el-col :span="4" class="topbar-left">
        <i class=""></i>
        <span class="title" @click="index()">通识选修课考试平台</span>
      </el-col>

      <el-col :span="20" class="topbar-right">
        <i class="el-icon-position" @click="toggle()"></i>

        <div class="user" v-if="isTrue">
          <img src="@/assets/img/1.jpg" class="user-img" ref="img" @click="showSetting()" />
          <transition name="fade">
            <div class="out" ref="out" v-show="login_flag">
              <ul>
                <li  @click="tompwd()"><a href="javascript:;">修改密码</a></li>
                <li class="exit" @click="exit()"><a href="javascript:;">退出</a></li>
              </ul>
            </div>
          </transition>
        </div>

        <div class="user" v-if="!isTrue">
          <img src="@/assets/img/Yuri11.jpg" class="user-img" ref="img" @click="showSetting()" />
          <transition name="fade">
            <div class="out" ref="out" v-show="login_flag">
              <ul>
                <li  @click="tompwd()"><a href="javascript:;">修改密码</a></li>
                <li class="exit" @click="exit()"><a href="javascript:;">退出</a></li>
              </ul>
            </div>
          </transition>
        </div>

      </el-col>

    </el-row>
  </header>
  -->
  <header id = "topbar">
    <div class="user" v-if="isTrue">
      <img src="@/assets/img/LLY2.jpg" class="user-img" ref="img" @click="showSetting()" />
      <transition name="fade">
        <div class="out" ref="out" v-show="login_flag">
          <ul>
            <li @click="index()"><a href="javascript:;">首页</a></li>
            <li  @click="tompwd()"><a href="javascript:;">修改密码</a></li>
            <li class="exit" @click="exit()"><a href="javascript:;">退出</a></li>
          </ul>
        </div>
      </transition>
    </div>

  <div class="user" v-if="!isTrue">
    <img src="@/assets/img/Yuri11.jpg" class="user-img" ref="img" @click="showSetting()" />
    <transition name="fade">
      <div class="out" ref="out" v-show="login_flag">
        <ul>
          <li @click="index()"><a href="javascript:;">首页</a></li>
          <li  @click="tompwd()"><a href="javascript:;">修改密码</a></li>
          <li class="exit" @click="exit()"><a href="javascript:;">退出</a></li>
        </ul>
      </div>
    </transition>
  </div>
  </header>
</template>

<script>
import store from '@/vuex/store'
import {mapState, mapMutations} from 'vuex'
export default {
  data() {
    return {
      login_flag: false,
      user: { //用户信息
        userName: null,
        userId: null,
      },
      isTrue: null
    }
  },
  created() {
    this.getUserInfo()
  },
  computed: mapState(["flag","menu"]),
  methods: {
    //显示、隐藏退出按钮
    showSetting() {
      this.login_flag = !this.login_flag
    },
    //左侧栏放大缩小
    ...mapMutations(["toggle"]),
    getUserInfo() { //获取用户信息
      let userName = this.$cookies.get("cname")
      let userId = this.$cookies.get("cid")
      let Role = this.$cookies.get("role")
      this.user.userName = userName
      this.user.userId = userId
      if(Role == 1) this.isTrue = true;
      else this.isTrue = false;
    },
    index() {
      this.$router.push({path: '/index'})
    },
    exit() {
      let role = this.$cookies.get("role")
      this.$router.push({path:"/"}) //跳转到登录页面
      this.$cookies.remove("cname") //清除cookie
      this.$cookies.remove("cid")
      this.$cookies.remove("role")
      if(role == 0) {
        this.menu.pop()
      }
    },
    tompwd() {
      let role = this.$cookies.get("role")
      if(role == 0) {
        this.$message({
          message: '该功能禁用!',
          type: 'error'
        })

        return ;
      }
      this.$router.push({path:"/mpwd"});
    },
  },
  store
}
</script>

<style scoped>
.fade-enter-active, .fade-leave-active {
  transition: opacity .5s;
}

.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}

#topbar {
  position: relative;
  z-index: 10;
  background-color: #545c64;
  height: 50px;
  line-height: 80px;
  color: white;
  box-shadow: 5px 0px 10px rgba(0, 0, 0, 0.5);
}

.user .out {
  font-size: 14px;
  position: absolute;
  top: 50px;
  right: 0px;
  background-color: #fff;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  padding: 10px;
  width: 70px;
  text-align: center;
  opacity: 80%;
}

.user .out ul {
  list-style: none;
}

.user .out ul > li {
  height: 26px;
  line-height: 26px;
}

.out a {
  text-decoration: none;
  color: #000;
}

.user-img {
  height: 50px;
  width: 50px;
  float: right;
  border-radius: 50%;
  cursor: pointer;
}

</style>

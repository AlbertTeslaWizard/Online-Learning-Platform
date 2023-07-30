<!--学生考试首页-->
<template>
  <div class="line">
    <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">

      <div class="h">
      <div class="f">
        <el-submenu index="1">
          <template slot="title">我的试卷</template>
          <el-menu-item index="1-1"><a href="javascript:;" @click="exam()" class="j">我的考试</a></el-menu-item>
          <el-menu-item index="1-2"><a href="javascript:;" @click="practice()" class="j">我的练习</a></el-menu-item>
        </el-submenu>
        <el-menu-item index="2"><router-link to="/wqset" class="j">我的收藏</router-link></el-menu-item>

        <el-submenu index="3">
          <template slot="title">我的成绩</template>
          <el-menu-item index="3-1"><router-link to="/scoreTable" class="j">成绩表</router-link></el-menu-item>
          <el-menu-item index="3-2"><a href="javascript:;" @click="tograde3()" class="j">折线图</a></el-menu-item>
        </el-submenu>

      <el-menu-item index="4"><router-link to="/video" class="j">我的视频</router-link></el-menu-item>
      <el-menu-item index="5"><router-link to="/message" class="j">我的笔记</router-link></el-menu-item>
      </div>

      <div class="e">
      <el-submenu index="5">
        <template slot="title">{{user.userName}}</template>
        <el-menu-item index="5-1"><a href="javascript:;" @click="manage()" class="j">修改密码</a></el-menu-item>
        <el-menu-item index="5-2"><a href="javascript:;" @click="exit()" class="j">退出</a></el-menu-item>
      </el-submenu>
      </div>
      </div>

    </el-menu>

    <div class="main">
      <router-view></router-view>
    </div>

  </div>
</template>

<script>
import myFooter from "@/components/student/myFooter"
import store from '@/vuex/store'
import {mapState} from 'vuex'
export default {
  store,
  components: {
    "v-footer": myFooter
  },
  data() {
    return {
      flag: false,
      user: {},
      activeIndex2: '1'
    }
  },
  created() {
    this.userInfo()
  },
  methods: {
    exit() {  //退出登录
      this.$router.push({path: "/"}) //跳转到登录页面
      this.$cookies.remove("cname") //清除cookie
      this.$cookies.remove("cid")
    },
    manage() {  //跳转到修改密码页面
      this.$router.push({path: '/manager'})
    },
    userInfo() {
      let studentName = this.$cookies.get("cname")
      let studentId = this.$cookies.get("cid")
      console.log(`studentId${studentId}`)
      console.log(`studentName ${studentName}`)
      this.user.userName = studentName
      this.user.studentId = studentId
    },
    practice() { //跳转练习模式
      let isPractice = true
      this.$store.commit("practice", isPractice)
      this.$router.push({path: '/startExam'})
    },
    exam() { //跳转考试模式
      let isPractice = false
      this.$store.commit("practice", isPractice)
      this.$router.push({path: '/student'})
    },
    tograde3() {
      this.$router.push({
        path: '/grade3',
        query: {
          studentId: this.$cookies.get("cid")
        }
      })
    },
    computed: mapState(["isPractice"])
  }
}
</script>

<style scoped>
.right .icon {
  margin-right: 6px;
}

.f {
  display: flex;
  justify-content:space-between;
}

.h {
  display:flex;
  justify-content:space-between;
}

.j {
  color: white;
}

#student .padding-50 {
  margin: 0 auto;
  padding: 0 50px;
  box-shadow: 0 0 10px 4px rgba(1,149,255,0.1);
  background-color: lightblue;
}
.list a {
  text-decoration: none;
  color: black;
}
li {
  list-style: none;
  height: 60px;
  line-height: 60px;
}
#student .list{
  display: flex;
}
#student .list li {
  padding: 0 20px;
  cursor: pointer;
}
#student .list li:hover {
  background-color: orangered;
  transition: all 2s ease;
}
#student .list li:hover a {
  color: #fff;
}
#student .list .right {
  margin-left: auto;
  position: relative;
}
#student .list li.right :hover a {
  color: #000;
}
#student .list .logo {
  display: flex;
  font-weight: bold;
  color: #2f6c9f;
}

#student .list .logo i {
  font-size: 50px;
}
.right .msg {
  text-align: center;
  position: absolute;
  top: 60px;
  left: 0px;
  display: flex;
  flex-direction: column;
  border-radius: 2px;
  border-bottom: 3px solid #0195ff;
  background-color: white;
}
.right .msg p {
  height: 40px;
  line-height: 40px;
  width: 105px;
}
.right .msg p:hover {
  background-color: #0195ff;
}

.main {
  background-color: white;
}
</style>

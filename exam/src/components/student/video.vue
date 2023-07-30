// 我的视频页面
<template>
  <div id="whole">
    <div class="adjust">

    </div>
    <ul class="video" v-loading="loading">
      <li class="item" v-for="(item, index) in pagination.records" :key="index">
        <div id="a">

          <div class ="b">
            <img :src="require(`@/assets/img/`+ item.imageName +`.jpg`)">
          </div>

          <div id="c">
            <div class="d"></div>
            <div class="title">{{map[item.videoName]}}</div>
            <div class="subTitle"><i class="el-icon-user"></i>{{item.teacherName}}</div>
            <div class="name"><i class="el-icon-place"></i>{{item.institution}}</div>
            <br>
            <br>
            <el-button type="primary" class="login" @click="jump(item.videoId)">观看课程</el-button>
          </div>

          <div class="status">
            <i class="el-icon-time"></i><span>正在进行</span>
          </div>
        </div>

      </li>
    </ul>

    <div class="pagination">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pagination.current"
        :page-sizes="[4, 6, 10, 20, 40]"
        :page-size="pagination.size"
        medium
        background
        layout="total, sizes, prev, pager, next, jumper"
        :total="pagination.total">
      </el-pagination>
    </div>

  </div>
</template>

<script>
export default {
  // name: 'myExam'
  data() {
    return {
      loading: false,
      pagination: { //分页后的考试信息
        current: 1, //当前页
        total: null, //记录条数
        size: 4 //每页条数
      },
      index: 0, //全局index
      map: ''
    }
  },
  created() {
    this.getVideoInfo()
    this.inital()
    this.loading = true
  },
  methods: {
    inital() {
      this.map = new Map();
      this.map[1] = '音乐鉴赏';
      this.map[2] = '从经典物理学到相对论';
      this.map[3] = '化学与生活';
      this.map[4] = '自然与人类';
      this.map[5] = '中国传统文化入门';
      this.map[6] = '航空与航天';
      this.map[7] = '美学原理';
      this.map[8] = '道德之行';
      this.map[9] = '现代自然地理学';
      this.map[10] = '影视鉴赏';
    },
    //获取当前所有视频信息
    getVideoInfo() {
      this.$axios(`/api/videos/${this.pagination.current}/${this.pagination.size}`).then(res => {
        this.pagination = res.data.data
        this.loading = false
        console.log(this.pagination)
      }).catch(error => {
        console.log(error)
      })
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val
      this.getVideoInfo()
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val
      this.getVideoInfo()
    },
    jump(videoId) {
      this.$router.push(
        {path: '/video1',
          query: {
            videoId: videoId
          }
        })
    },
  }
}
</script>

<style lang="scss" scoped>
.pagination {
  padding: 150px 0px 0px 550px;
  .el-pagination {
    display: flex;
  }
}

.adjust {
  height: 180px;
}

.box {
  line-height: 3em;
}

#whole {
  background: transparent;
  background: url("../../assets/img/landscape11.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  height: 715px;
}

#whole .video {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}

#a {
  width: 226px;
  height: 300px;
  border-style: solid;
  border-color: transparent;
  border-width: thin;
  border-radius: 10px;
  overflow: hidden;
  background-color: transparent;
}

.b {
  width: 100%;
  height: 120px;
  background-repeat: no-repeat;
  background-size: contain;
  opacity: 80%;
}

.b img {
  width: 100%;
  height: 100%;
}

#c {
  background-color: transparent;
  padding-left: 8px;
}

.d {
  height: 5px;
}

.title{
  line-height: 2em;
  font-size: 18px;
  font-style: italic;
  font-weight: bold;
}

.subTitle{
  font-size: 15px;
  line-height: 1em;
  color: #333;
}

.name{
  font-size: 15px;
  line-height: 1em;
  color: #333;
}

.login {
  float: right;
}

.status {
  padding-top: 50px;
  padding-bottom: 0px;
  padding-left: 8px;
  font-size: 12px;
  color: #53B880;
}

</style>

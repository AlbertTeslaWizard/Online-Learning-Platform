// 我的练习页面
<template>
  <div id="myExam">
    <div class="wrapper">
      <ul class="top">
        <li class="order">练习列表</li>
        <li class="search-li"><div class="icon"><input style="outline:none;" type="text" placeholder="试卷名称" class="search" v-model="key"><i class="el-icon-search"></i></div></li>
        <li><el-button type="success" @click="search()">搜索试卷</el-button></li>
      </ul>
      <ul class="paper" v-loading="loading">
        <li class="item" v-for="(item, index) in pagination.records" :key="index">

          <div class="adjust">

            <div class="image">
              <img :src="require(`@/assets/img/`+ `19` +`.jpg`)">
            </div>

            <div class="info">
              <div class="z"><i class="el-icon-s-platform"></i>{{item.source}}</div><br>
              <div class="a"><i class="el-icon-document"></i>试卷总分: {{item.totalScore}}分</div><br>
              <div class="b" v-if="item.totalTime != null"><i class="el-icon-time"></i>时间: {{item.totalTime}}分钟</div><br>
              <div class="c"><i class="el-icon-collection-tag"></i>试卷类型: {{item.type}}</div><br>
              <el-button size="small" type="primary" class="yuri" @click="toAnswer(item.examCode)">开始练习</el-button>
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
  </div>
</template>

<script>
export default {
  // name: 'myExam'
  data() {
    return {
      loading: false,
      key: null, //搜索关键字
      allExam: null, //所有考试信息
      pagination: { //分页后的考试信息
        current: 1, //当前页
        total: null, //记录条数
        size: 4 //每页条数
      },
      topicCount: [],//每种类型题目的总数
      examData: { //考试信息
        // source: null,
        // totalScore: null,
      },
      index: 0, //全局index
      topic: {  //试卷信息

      },
    }
  },
  created() {
    this.getExamInfo()
    this.loading = true
  },
  methods: {
    //获取当前所有考试信息
    getExamInfo() {
      this.$axios(`/api/exam/${this.pagination.current}/${this.pagination.size}`).then(res => {
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
      this.getExamInfo()
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val
      this.getExamInfo()
    },
    //搜索试卷
    search() {
      this.$axios('/api/exams').then(res => {
        if(res.data.code == 200) {
          let allExam = res.data.data
          let newPage = allExam.filter(item => {
            return item.source.includes(this.key)
          })
          this.pagination.records = newPage
        }
      })
    },
    //跳转到试卷详情页
    toAnswer(examCode) {
      this.$router.push(
        {path: '/answer',
          query: {
            examCode: examCode
          }
        })
      console.log(examCode)
    }
  }
}
</script>

<style lang="scss" scoped>
.pagination {
  padding: 90px 30px 0px 0px;
  .el-pagination {
    display: flex;
    justify-content: center;
  }
}

.wrapper .top .order {
  cursor: pointer;
}

.item .info i {
  margin-right: 3px;
  color: dodgerblue;
}

.adjust {
  width: 226px;
  height: 300px;
  border-style: solid;
  border-color: transparent;
  border-width: thin;
  border-radius: 10px;
  overflow: hidden;
  background-color: transparent;
}

.adjust:hover {
  box-shadow: 0 0 4px 2px transparent;
  transform: scale(1.05);
}

.wrapper .paper {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}

.top .el-icon-search {
  position: absolute;
  right: 10px;
  top: 10px;
}

.top .icon {
  position: relative;
}

.wrapper .top {
  border-bottom: 1px solid red;
  margin-bottom: 20px;
  display: flex;
}

#myExam .search-li {
  margin-left: auto;
}

.top .search-li {
  margin-left: auto;
}

.top li {
  display: flex;
  align-items: center;
}

.top .search {
  margin-left: auto;
  padding: 10px;
  border-radius: 60px;
  border: 1px solid #eee;
  box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
  transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}

.top .search:hover {
  border-style: solid;
  border-width: thin;
  border-color: red;
}

.wrapper .top li {
  margin: 20px;
}

#myExam {
  width: 100%;
  background: url("../../assets/img/landscape17.jpg");
  background-size: cover;
  height: 710px;
}

#myExam .wrapper {
  background-color: transparent;
}

.yuri {
  margin-left: 150px;
  margin-top: 25px;
}

.a {
  height: 3px;
  color: #333;
}

.b {
  height: 3px;
  color: #666;
}

.z {
  line-height: 1em;
  font-size: 18px;
  font-style: italic;
  font-weight: bold;
}

.c {
  height: 3px;
  color: #666;
}

.info {
  font-size: 14px;
}

.image {
  width: 100%;
  height: 120px;
  background-repeat: no-repeat;
  background-size: contain;
  opacity: 80%;
}

.image img {
  height: 100%;
  width: 100%;
}

.paper {
  padding-top: 100px;
}

</style>

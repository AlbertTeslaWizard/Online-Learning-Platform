<template>
  <div class="score">

    <div class="title">
      <p class="a">{{examData.source}}</p>
      <p class="b">总分: {{totalScore}}分 考试时长: {{examData.totalTime}}分钟</p>
      <p class="c">考生: {{userInfo.name}}</p>
    </div>

    <div class="total">
      <div class="show">

        <div class="img1" :class="{'img1Transform': imgShow}">
          <img :src="imgSrc.fail1" alt="哭了" v-if="score < passScore">
          <img :src="imgSrc.pass1" alt="过了" v-if="score >= passScore">
        </div>

        <div class="number" :class="{'border': isTransition}">
          <span>{{score}}</span>
          <span>分数</span>
        </div>

        <div class="img2" :class="{'img2Transform': imgShow}">
          <img :src="imgSrc.fail2" alt="哭了" v-if="score < passScore">
          <img :src="imgSrc.pass2" alt="过了" v-if="score >= passScore">
        </div>

      </div>

      <ul class="time">
        <li class="start"><span>开始时间</span> <span>{{startTime}}</span></li>
        <li class="end"><span>结束时间</span> <span>{{endTime}}</span></li>
      </ul>

    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      isTransition: false, //是否渲染完成
      score: 0, //总分
      imgShow: false, //不及格图片显示
      imgSrc: {
        fail1: require("@/assets/img/Yuri9.jpg"),
        fail2: require('@/assets/img/Yuri10.jpg'),
        pass1: require('@/assets/img/campus1.jpg'),
        pass2: require('@/assets/img/campus2.jpg')
      },
      startTime: null, //考试开始时间
      endTime: null, //考试结束时间
      topicCount: [],//每种类型题目的总数
      grade: [],  //每种类型分数的总数
      userInfo: { //用户信息
        name: null,
        id: null
      },
      examData: { //考试信息
        // source: null,
        // totalScore: null,
      },
      topic: {  //试卷信息

      },
      totalScore: 0,
      passScore: 0,
    }
  },
  created() {
    this.transiton()
    this.getCookies()
    this.getScore()
  },
  mounted() {
    this.init()
  },
  methods: {
    transiton() {  //一秒后过渡
      setTimeout(() => {
        this.isTransition = true
        this.imgShow = true
      },1000)
    },
    getCookies() {  //获取cookie
      this.userInfo.name = this.$cookies.get("cname")
      this.userInfo.id = this.$cookies.get("cid")
    },
    getScore() {
      let score = this.$route.query.score
      let startTime = this.$route.query.startTime
      let endTime = this.$route.query.endTime
      this.score = score
      this.startTime = startTime
      this.endTime = endTime
    },
    init() {
      let examCode = this.$route.query.examCode //获取路由传递过来的试卷编号
      this.$axios(`/api/exam/${examCode}`).then(res => {  //通过examCode请求试卷详细信息
        this.examData = { ...res.data.data}
        let paperId = this.examData.paperId
        this.$axios(`/api/paper/${paperId}`).then(res => {  //通过paperId获取试题题目信息
          this.topic = {...res.data}
          let keys = Object.keys(this.topic) //对象转数组
          keys.forEach(e => {
            let data = this.topic[e]
            this.topicCount.push(data.length)
            let currentScore = 0
            for(let i = 0; i < data.length; i ++) { //循环每种题型,计算出总分
              currentScore += data[i].score
            }
            this.grade.push(currentScore) //把每种题型总分存入grade
            this.totalScore += currentScore;
            this.passScore += currentScore * 0.6;
          })
        })
      })
    },
  }
}
</script>

<style lang="scss" scoped>
.show {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 30px;
  background-color: transparent;

  .img1Transform {
    opacity: 1 !important;
    transform: translateX(30px) !important;
    transition: all 0.6s ease !important;
  }
  .img2Transform {
    opacity: 1 !important;
    transform: translateX(-30px) !important;
    transition: all 0.6s ease !important;
  }
}

img {
  width: 240px;
  height: 180px;
}

.img1 {
  margin-top: 60px;
  margin-left: 300px;
  opacity: 70%;
  transform: translateX(0px);
  transition: all 0.6s ease;
}

.img2 {
  margin-top: 60px;
  margin-right: 300px;
  opacity: 70%;
  transform: translateX(0px);
  transition: all 0.6s ease;
}

.a {
  font-size: 25px;
  font-weight: bold;
}

.b {
  font-size: 18px;
}

.c {
  font-size: 18px;
  margin-left: 10px;
}

.time {
  padding: 50px 70px;

  li {
    display: flex;
    justify-content: space-around;
    padding: 10px;
    margin: 20px 0px;
  }

  li:nth-child(1) {
    background-color: #e9f5e9;
  }

  li:nth-child(2) {
    background-color: #fcf8e3;
  }

}

.border {
  border: 6px solid rgb(220, 20, 60) !important;
  transition: all 2s ease;
  width: 160px !important;
  height: 160px !important;
  transform: rotate(360deg) !important;
  opacity: 1 !important;
}

.score {
  background: url("../../assets/img/landscape4.jpg");
  height: 653px;
}

.total {
  background-color: transparent;
}

.number {
  opacity: 0;
  border: 6px solid transparent;
  transform: rotate(0deg);
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  margin: 0 auto;
  width: 160px;
  height: 160px;
  border-radius: 50%;
  margin-top: 40px;
  margin-bottom: 30px;
  transition: all 1s ease;

  span:nth-child(1) {
    font-size: 36px;
    font-weight: 600;
  }

  span:nth-child(2) {
    font-size: 14px;
  }
}

.title {
  text-align: center;
}

</style>


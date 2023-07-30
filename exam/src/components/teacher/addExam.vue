<!-- 添加考试 -->
<template>
  <section class="add">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">考试编号*</span>
        </span>
        <el-input v-model="form.examCode" placeholder="请输入试卷编号"></el-input>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">考试名称*</span>
        </span>
        <el-input v-model="form.source" placeholder="请输入试卷名称"></el-input>
      </el-form-item>

      <!--
      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">所属学院*</span>
        </span>
        <el-input v-model="form.institute" placeholder="请输入所属学院"></el-input>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">所属专业*</span>
        </span>
        <el-input v-model="form.major" placeholder="请输入所属专业"></el-input>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">所属年级*</span>
        </span>
        <el-input v-model="form.grade" placeholder="请输入所属年级"></el-input>
      </el-form-item>


      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">考试日期*</span>
        </span>
        <el-col :span="24">
          <el-date-picker placeholder="请选择考试日期" v-model="form.examDate" style="width: 100%;"></el-date-picker>
        </el-col>
      </el-form-item>
      -->

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">考试时长*</span>
        </span>
        <el-input v-model="form.totalTime"  placeholder="请输入考试时长"></el-input>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">总分*</span>
        </span>
        <el-input v-model="form.totalScore"  placeholder="请输入考试总分"></el-input>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">考试类型*</span>
        </span>
        <el-input v-model="form.type" placeholder="请输入考试类型"></el-input>
      </el-form-item>

      <div class="adjust">
      <el-form-item>
        <el-button type="primary" @click="onSubmit()">立即创建</el-button>
        <el-button type="danger" @click="cancel()">取消</el-button>
      </el-form-item>
      </div>

    </el-form>
  </section>
</template>

<script>
export default {
  data() {
    return {
      form: { //表单数据初始化
        examCode: null,
        source: null,
        description: null,
        institute: null,
        major: null,
        grade: null,
        examDate: null,
        totalTime: null,
        totalScore: null,
        type: null,
        tips: null,
        paperId: null,
      }
    };
  },
  methods: {
    formatTime(date) { //日期格式化
      let year = date.getFullYear();
      let month= date.getMonth()+ 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
      let day=date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
      let hours=date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
      let minutes=date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
      let seconds=date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      // 拼接
      return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
    },
    onSubmit() {
      this.$axios(`/api/examManagePaperId`).then(res => {
        this.form.paperId = res.data.data.paperId + 1 //实现paperId自增1
        console.log(this.form.paperId);
        this.$axios({
          url: '/api/exam',
          method: 'post',
          data: {
            ...this.form
          }
        }).then(res => {
          if(res.data.code == 200) {
            this.$message({
              message: '数据添加成功',
              type: 'success'
            })
            this.$router.push({path: '/selectExam'})
          }
        })
      })
    },
    cancel() { //取消按钮
      this.form = {}
    },

  }
};
</script>
<style lang="scss" scoped>
.add {
  padding-left: 400px;
  padding-top: 160px;
  width: 400px;
  opacity: 90%;
}

.adjust {
  text-align: center;
}

.el-form-item {
  margin-bottom: 10px;
}
</style>

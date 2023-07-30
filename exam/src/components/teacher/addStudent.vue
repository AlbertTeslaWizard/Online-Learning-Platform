<!-- 添加学生 -->
<template>
  <section class="add">
    <el-form ref="form" :model="form" label-width="80px">

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">学号*</span>
        </span>
        <el-input v-model="form.studentId" placeholder="请输入学号"></el-input>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">姓名*</span>
        </span>
        <el-input v-model="form.studentName" placeholder="请输入姓名"></el-input>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">性别*</span>
        </span>
        <el-input v-model="form.sex" placeholder="请输入性别"></el-input>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">学院*</span>
        </span>
        <el-input v-model="form.institute" placeholder="请输入学院"></el-input>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">专业*</span>
        </span>
        <el-input v-model="form.major" placeholder="请输入专业"></el-input>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">年级*</span>
        </span>
        <el-input v-model="form.grade" placeholder="请输入年级"></el-input>
      </el-form-item>

      <el-form-item label="班级">
        <span slot="label">
          <span style="font-size: 15px; color: red">班级*</span>
        </span>
        <el-input v-model="form.clazz" placeholder="请输入班级"></el-input>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">密码*</span>
        </span>
        <el-input v-model="form.pwd" placeholder="请输入密码"></el-input>
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
        studentName: null,
        grade: null,
        major: null,
        clazz: null,
        institute: null,
        tel: null,
        email: null,
        pwd: null,
        cardId: null,
        sex: null,
        role: 2
      }
    };
  },
  methods: {
    onSubmit() { //数据提交
      this.$axios({
        url: '/api/student',
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
          this.$router.push({path: '/studentManage'})
        }
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
  width: 350px;
  padding-top: 100px;
  padding-left: 400px;
  opacity: 90%;
}

.el-form-item {
  margin-bottom: 10px;
}

.adjust {
  text-align: center;
}
</style>


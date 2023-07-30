<!-- 添加教师 -->
<template>
  <section class="add">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">教师工号*</span>
        </span>
        <el-input v-model="form.teacherId" placeholder="请输入教师工号"></el-input>
      </el-form-item>
      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">姓名*</span>
        </span>
        <el-input v-model="form.teacherName" placeholder="请输入姓名"></el-input>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">学院*</span>
        </span>
        <el-input v-model="form.institute" placeholder="请输入学院"></el-input>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">性别*</span>
        </span>
        <el-input v-model="form.sex" placeholder="请输入性别"></el-input>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">密码*</span>
        </span>
        <el-input v-model="form.pwd" placeholder="请输入密码"></el-input>
      </el-form-item>

      <el-form-item label="职称">
        <span slot="label">
          <span style="font-size: 15px; color: red">职称*</span>
        </span>
        <el-input v-model="form.type" placeholder="请输入职称"></el-input>
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
      pagination: {
        //分页后的考试信息
        current: 1, //当前页
        total: null, //记录条数
        size: 6, //每页条数
      },
      form: { //表单数据初始化
        teacherId: null,
        teacherName: null,
        grade: null,
        major: null,
        clazz: null,
        institute: null,
        tel: null,
        email: null,
        pwd: null,
        cardId: null,
        sex: null,
        role: 1
      },
      Info: [],
    };
  },
  created() {
    this.getTeacherInfo();
  },
  methods: {
    getTeacherInfo() {
      //分页查询所有试卷信息
      this.$axios(`/api/teachers/${this.pagination.current}/${this.pagination.size}`).then(res => {
        this.pagination = res.data.data;
        this.Info = res.data.data.records;
        console.log(this.Info);
      }).catch(error => {});
    },
    onSubmit() { //数据提交
      let len = this.Info.length
      console.log(len);

      for(let i = 0; i < len; i ++) {
        if(this.form.teacherId == this.Info[i].teacherId) {
          this.$message({
            message: '该教师工号已存在!',
            type: 'error'
          })

          return ;
        }
      }

      this.$axios({
        url: '/api/teacher',
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
          this.$router.push({path: '/teacherManage'})
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
  padding-top: 150px;
  padding-left: 400px;
  width: 400px;
  opacity: 90%;
}

.adjust {
  text-align: center;
}

</style>


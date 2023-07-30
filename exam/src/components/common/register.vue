<!-- 注册账号 -->
<template>
  <div class = "register">
  <section class="add">
    <el-form ref="form" :model="form" label-width="100px">
      <el-form-item>
        <span slot="label">
          <span style="color: red">学号*</span>
        </span>
        <el-input v-model="form.studentId" placeholder="请输入学号(注意学号要在int类型范围内)" style="width: 100%;"  />
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="color: red">密码*</span>
        </span>
        <el-input v-model="form.pwd" placeholder="请输入密码" style="width: 100%;"></el-input>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="color: red">姓名*</span>
        </span>
        <el-input v-model="form.studentName" placeholder="请输入姓名"></el-input>
      </el-form-item>

      <el-form-item>
         <span slot="label">
          <span style="color: red">学院*</span>
         </span>
        <el-input v-model="form.institute" placeholder="请输入学院名"></el-input>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="color: red">专业*</span>
         </span>
        <el-input v-model="form.major" placeholder="请输入专业名"></el-input>
      </el-form-item>

      <el-form-item>
          <span slot="label">
          <span style="color: red">年级*</span>
         </span>
        <el-input v-model="form.grade" placeholder="请输入年级"></el-input>
      </el-form-item>

      <el-form-item>
         <span slot="label">
          <span style="color: red">班级*</span>
         </span>
        <el-input v-model="form.clazz" placeholder="请输入班级"></el-input>
      </el-form-item>

      <el-form-item>
         <span slot="label">
          <span style="color: red">性别*</span>
         </span>
        <el-input v-model="form.sex" placeholder="请输入性别"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit()">注册</el-button>
        <el-button type="danger" @click="cancel()">取消</el-button>
      </el-form-item>

    </el-form>

  </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: { //表单数据初始化
        studentId: null,
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
  created() {
    //this.getStudentInfo();
  },
  methods: {
    getStudentInfo() {
      //查询所有学生学号信息
      let R;
      this.$axios(`/api/students`).then(res => {
        R = {...res.data.data};

        var array = [];
        array = R;
        let len = res.data.data.length;

        for (let i = 0; i < len; i++) {
          console.log(array[i].studentId);
        }
      }).catch(error => {
      });
    },
    onSubmit() { //数据提交
      if (this.form.pwd == null || this.form.studentId == null) {
        this.$message({
          type: 'error',
          message: '学号或密码不能为空!'
        })
      }

      if (this.form.pwd != null && this.form.studentId != null) {
        let R;
        this.$axios(`/api/students`).then(res => {
          R = {...res.data.data};

          var array = [];
          array = R;
          let len = res.data.data.length;

          for (let i = 0; i < len; i++) {
            if (array[i].studentId == this.form.studentId) {
              this.$message({
                message: '该学号已存在!',
                type: 'error'
              })

              return ;
            }
          }
        }).catch(error => {
        });

        this.$axios({
          url: '/api/student',
          method: 'post',
          data: {
            ...this.form
          }
        }).then(res => {
          if(res.data.code == 200) {
            this.$message({
              message: '注册成功',
              type: 'success'
            })
            this.$router.push({path: '/'})
          }
        })
      }
    },
    cancel() { //取消按钮
      this.form = {}
    }
  },
};
</script>
<style lang="scss" scoped>
.add {
  padding: 20px 50px;
  margin: 70px 0 0 240px;
  background: rgba(255, 255, 255, 0);
  width: 1000px;
  opacity: 100%;
}

.register {
  position: fixed;
  width: 100%;
  height: 100%;
  background: url("../../assets/img/campus.jpg");
  background-size: cover;
}
</style>


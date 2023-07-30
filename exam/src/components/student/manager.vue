<!--管理中心-->
<template>
  <div class='manager'>
    <section class="adjust">
    <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="100px" class="demo-ruleForm">

      <el-form-item>
          <span slot="label">
          <span style="color: red; font-size: 18px;">原密码*</span>
          </span>
        <el-input v-model="ruleForm2.originpass" style="width:300px;"></el-input>
      </el-form-item>

      <el-form-item prop="pass">
          <span slot="label">
          <span style="color: red; font-size: 18px;">新密码*</span>
          </span>
        <el-input type="password" v-model="ruleForm2.pass" style="width:300px;"></el-input>
      </el-form-item>

      <el-form-item label="确认密码" prop="checkPass">
        <span slot="label">
          <span style="color: red; font-size: 18px;">确认密码*</span>
        </span>
        <el-input type="password" v-model="ruleForm2.checkPass" style="width:300px;"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button class="bt2" size= "small" type="success" @click="resetForm('ruleForm2')">重置</el-button>
        <el-button class="bt1" size= "small" type="primary" @click="submitForm('ruleForm2')">提交</el-button>
      </el-form-item>

    </el-form>
    </section>

  </div>
</template>

<script>
  export default {
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm2.checkPass !== '') {
            this.$refs.ruleForm2.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm2.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        ispass: true,
        ruleForm2: {
          originpass: '',
          pass: '',
          checkPass: ''
        },
        rules2: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ]
        },
        form: {

        },
      };
    },
    created() {
      this.inital()
    },
    methods: {
      inital() {
        let studentId = this.$cookies.get("cid");
        console.log(studentId);
        this.$axios(`/api/student/${studentId}`).then(res => {
          this.form = res.data.data
        })
      },

      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let studentId = this.$cookies.get("cid")
            if(this.ruleForm2.originpass !== this.form.pwd) {
              this.$message({
                message: '输入的原密码不正确',
                type: 'error'
              })
              return false;
            }
            this.$axios({ //修改密码
              url: '/api/studentPWD',
              method: 'put',
              data: {
                pwd: this.ruleForm2.pass,
                studentId
              }
            }).then(res => {
              if(res.data != null ) { //修改成功提示
                this.$message({
                  message: '密码修改成功',
                  type: 'success'
                })
              }
            })
          } else {
            console.log('提交错误!');
            return false;
          }
        });
      },

      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style scoped>

.manager {
  width: 100%;
  height: 700px;
  background: url("../../assets/img/landscape5.jpg");
  padding-top: 100px;
}

.adjust {
  width: 300px;
  padding-left: 500px;
  padding-top: 118px;
}

.bt1 {
  float: right;
}

.bt2 {
  float: right;
}

.el-form-item {
  opacity: 80%;
}

</style>

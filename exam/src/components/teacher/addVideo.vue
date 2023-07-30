<!-- 添加学生 -->
<template>
  <section class="add">

    <el-form ref="form" :model="form" label-width="80px">

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">视频名称*</span>
        </span>

        <el-select v-model="form.videoName" placeholder="请选择视频名称" style="width:100%">
          <el-option label="音乐鉴赏" value="1"></el-option>
          <el-option label="从经典物理学到相对论" value="2"></el-option>
          <el-option label="化学与生活" value="3"></el-option>
          <el-option label="自然与人类" value="4"></el-option>
          <el-option label="中国传统文化入门" value="5"></el-option>
          <el-option label="航空与航天" value="6"></el-option>
          <el-option label="美学原理" value="7"></el-option>
          <el-option label="道德之行" value="8"></el-option>
          <el-option label="现代自然地理学" value="9"></el-option>
          <el-option label="影视鉴赏" value="10"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">图片名称*</span>
        </span>
        <el-select v-model="form.imageName" placeholder="请选择图片名称" style="width:100%">
          <el-option label="图片一" value="1"></el-option>
          <el-option label="图片二" value="2"></el-option>
          <el-option label="图片三" value="3"></el-option>
          <el-option label="图片四" value="4"></el-option>
          <el-option label="图片五" value="5"></el-option>
          <el-option label="图片六" value="6"></el-option>
          <el-option label="图片七" value="7"></el-option>
          <el-option label="图片八" value="8"></el-option>
          <el-option label="图片九" value="9"></el-option>
          <el-option label="图片十" value="10"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">授课教师*</span>
        </span>
        <el-select v-model="form.teacherName" placeholder="请选择授课教师" style="width:100%">
          <el-option label="中村由利" value="中村由利"></el-option>
          <el-option label="赵峥" value="赵峥"></el-option>
          <el-option label="刘旦初" value="刘旦初"></el-option>
          <el-option label="欧阳自远" value="欧阳自远"></el-option>
          <el-option label="李庚其" value="李庚其"></el-option>
          <el-option label="艾剑良" value="艾剑良"></el-option>
          <el-option label="叶朗" value="叶朗"></el-option>
          <el-option label="陈文娟" value="陈文娟"></el-option>
          <el-option label="王建" value="王建"></el-option>
          <el-option label="陈旭光" value="陈旭光"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <span slot="label">
          <span style="font-size: 15px; color: red">单位名称*</span>
        </span>
        <el-select v-model="form.institution" placeholder="请选择单位名称" style="width:100%">
          <el-option label="大阪音乐学院" value="大阪音乐学院"></el-option>
          <el-option label="北京师范大学" value="北京师范大学"></el-option>
          <el-option label="复旦大学" value="复旦大学"></el-option>
          <el-option label="中国科学院" value="中国科学院"></el-option>
          <el-option label="北京大学" value="北京大学"></el-option>
          <el-option label="中央财经大学" value="中央财经大学"></el-option>
          <el-option label="南京师范大学" value="南京师范大学"></el-option>
        </el-select>
      </el-form-item>

      <div class="adjust">
      <el-form-item>
        <el-button type="primary" @click="onSubmit()">立即上传</el-button>
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
        videoName: null,
        imageName: null,
        teacherName: null,
        institution: null,
      },
    };
  },
  methods: {
    onSubmit() { //数据提交
      this.$axios({
        url: '/api/video',
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
          this.$router.push({path: '/videoManage'})
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
  padding-top: 150px;
  padding-left: 400px;
  opacity: 90%;
}

.adjust {
  text-align: center;
}
</style>


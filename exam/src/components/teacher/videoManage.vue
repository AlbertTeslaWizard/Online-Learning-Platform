// 视频管理页面
<template>
  <div class="all">
    <el-table :data="pagination.records" >
      <el-table-column fixed="left" prop="videoName" label="视频名称" width="150"></el-table-column>
      <el-table-column prop="imageName" label="图片名称" width="150"></el-table-column>
      <el-table-column prop="teacherName" label="授课教师" width="150"></el-table-column>
      <el-table-column prop="institution" label="单位" width="150"></el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button @click="checkGrade(scope.row.videoId)" type="primary" size="small">编辑</el-button>
          <el-button @click="deleteById(scope.row.videoId)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pagination.current"
      :page-sizes="[6, 10]"
      :page-size="pagination.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pagination.total"
      class="page">
    </el-pagination>
    <!-- 编辑对话框-->
    <el-dialog
      title="编辑视频信息"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <section class="update">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="视频名称">
            <el-input v-model="form.videoName"></el-input>
          </el-form-item>
          <el-form-item label="图片名称">
            <el-input v-model="form.imageName"></el-input>
          </el-form-item>
          <el-form-item label="授课教师">
            <el-input v-model="form.teacherName"></el-input>
          </el-form-item>
          <el-form-item label="单位">
            <el-input v-model="form.institution"></el-input>
          </el-form-item>
        </el-form>
      </section>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
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
      dialogVisible: false, //对话框
      form: {},
    };
  },
  created() {
    this.getVideoInfo();
  },
  methods: {
    getVideoInfo() {
      //分页查询所有试卷信息
      this.$axios(`/api/videos/${this.pagination.current}/${this.pagination.size}`).then(res => {
        this.pagination = res.data.data;
      }).catch(error => {});
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val;
      this.getVideoInfo();
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val;
      this.getVideoInfo();
    },
    checkGrade(videoId) { //修改视频信息
      this.dialogVisible = true
      this.$axios(`/api/video/${videoId}`).then(res => {
        this.form = res.data.data
      })
    },
    deleteById(videoId) { //删除当前视频
      this.$confirm("确定删除当前视频吗？删除后无法恢复","Warning",{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'danger'
      }).then(()=> { //确认删除
        this.$axios({
          url: `/api/video/${videoId}`,
          method: 'delete',
        }).then(res => {
          this.getVideoInfo()
        })
      }).catch(() => {

      })
    },
    submit() { //提交更改
      this.dialogVisible = false
      this.$axios({
        url: '/api/video',
        method: 'put',
        data: {
          ...this.form
        }
      }).then(res => {
        console.log(res)
        if(res.data.code ==200) {
          this.$message({
            message: '更新成功',
            type: 'success'
          })
        }
        this.getVideoInfo()
      })
    },
    handleClose(done) { //关闭提醒
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        }).catch(_ => {});
    },
  }
};
</script>
<style lang="scss" scoped>
.all {
  width: 60%;
  height: 100%;
  padding-top: 115px;
  padding-left: 240px;
}

.el-table .warning-row {
  background: #000 !important;
}

.el-table .success-row {
  background: #dd5862;
}

.el-table {
  opacity: 90%;
}

.edit {
  margin-left: 20px;
}

.el-table tr {
  background-color: #dd5862 !important;
}

.page {
  margin-top: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}

</style>

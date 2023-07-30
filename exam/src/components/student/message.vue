//留言页面
<template>
  <div id="message">
    <div class="wrapper">
      <div class="all">
        <ul class="msglist">
          <li class="list"
          @mouseenter="enter(index)"
          @mouseleave="leave(index)"
          v-for="(data, index) in msg" v-if="data.studentId == Id" :key="index"
          >
            <p class="title"> <i class="iconfont icon-info"></i>{{data.title}}</p>
            <p class="content">{{data.content}}</p>

            <div style="text-align: right; margin: 0">
              <el-button @click="to(data.id)" type="primary" size="small" round>展开</el-button>
            <el-button @click="deleteById(data.id)" type="danger" size="small" round>删除</el-button>
              <el-button @click="alter(data.id)" type="warning" size="small" round>编辑</el-button>
              <el-button @click="reply(data.id)" type="success" size="small" round>注释</el-button>
            </div>

            <p class="date"><i class=""></i>{{data.time.substr(0, 10)}}</p>
            <div v-for="(replyData, index2) in data.replies" :key="index2">
              <p class="comment">{{replyData.reply}}</p>
            </div>
            <!--<span class="reply" @click="reply(data.id)" v-if="flag && index == current">注释</span>-->
          </li>
        </ul>
      </div>

      <div id="title1">
        <el-input
          type="textarea"
          :rows="2"
          placeholder="笔记标题"
          v-model="title"
          clearable>
        </el-input>
      </div>

      <div id="content1">
        <el-input
          type="textarea"
          :rows="5"
          placeholder="笔记内容"
          v-model="content"
          clearable>
        </el-input>
      </div>

      <div class="btn" style="text-align: right; margin: 0">
        <el-button type="success" round @click="submit()" size="medium">提交</el-button>
      </div>

      <div class="pagination">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagination.current"
          :page-sizes="[4,6,8,10]"
          :page-size="pagination.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pagination.total">
        </el-pagination>
      </div>
    </div>

    <!-- 编辑对话框-->
    <el-dialog
      title="编辑笔记信息"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <section class="update">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="标题">
            <el-input v-model="form.title"></el-input>
          </el-form-item>
          <el-form-item label="内容">
            <el-input v-model="form.content"></el-input>
          </el-form-item>
        </el-form>
      </section>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit1()">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>

export default {
  // name: 'message'
  data() {
    return {
      flag: false,
      current: 0,
      title: "",
      content: "",
      studentId: null,
      Id: null,
      pagination: { //分页后的留言列表
        current: 1, //当前页
        total: null, //记录条数
        size: 6 //每页条数
      },
      msg: [],
      dialogVisible: false,
      form: {},
    }
  },
  created() {
    this.userInfo()
    this.getMsg()
  },
  methods: {
    userInfo() {
      this.Id = this.$cookies.get("cid")
    },
    getMsg() {
      this.$axios(`/api/messages/${this.pagination.current}/${this.pagination.size}`).then(res => {
        let status = res.data.code
        console.log(res.data.data);
        if(status == 200) {
          this.msg = res.data.data.records
          this.pagination = res.data.data
        }
      })
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val
      this.getMsg()
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val
      this.getMsg()
    },
    submit() {
      let date = new Date()
      if(this.title.length == 0 || this.content.length == 0) { //非空判断
        this.$message({
          type: 'error',
          message: '标题或内容不能为空',
        })
       } else {
      this.$axios({
        url: "/api/message",
        method: "post",
        data: {
          title: this.title,
          content: this.content,
          time: date,
          studentId: this.Id
        }
      }).then(res => {
        let code = res.data.code
        if(code == 200) {
          this.$message({
            type: "success",
            message: "添加笔记成功"
          })
        }
        this.getMsg()
      })
    }
      this.title = ""
      this.content = ""
      this.getMsg()
    },
    submit1() {
      this.dialogVisible = false
      this.$axios({
        url: '/api/message',
        method: 'put',
        data: {
          ...this.form
        }
      }).then(res => {
        console.log(res);
        if(res.data.code ==200) {
          this.$message({
            message: '更新成功',
            type: 'success'
          })
        }
        this.getMsg()
      })
    },
    reply(messageId) { //回复留言功能
      this.$prompt('添加注释', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^[\s\S]*.*[^\s][\s\S]*$/,
        inputErrorMessage: '注释不能为空'
      }).then(({ value }) => {
        let date = new Date()
        console.log(messageId)
        this.$axios({
          url: '/api/reply',
          method: 'post',
          data: {
            reply: value,
            replyTime: date,
            messageId: messageId
          }
        }).then(res => {
          this.getMsg()
        })
        this.$message({
          type: 'success',
          message: '注释成功'
        });
      }).catch(() => {
        this.$message({
          type: 'warning',
          message: '取消输入'
        });
      });
    },
    deleteById(Id) {
      this.$confirm("确定删除当前信息吗？删除后无法恢复","Warning",{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'danger'
      }).then(()=> { //确认删除
        this.$axios({
          url: `/api/message/${Id}`,
          method: 'delete',
        }).then(res => {
          this.getMsg()
        })
      }).catch(() => {

      })
    },
    enter(index) {
      this.flag = true
      this.current = index
    },
    leave(index) {
      this.flag = false;
      this.current = index;
    },
    to(id) {
      this.$router.push (
        {
          path: '/messageshow',
          query: {
            Id: id
          }
        }
      )
    },
    alter(messageId) {
      this.dialogVisible = true
      this.$axios(`/api/message/${messageId}`).then(res => {
        this.form = res.data.data
        this.form.id = messageId;
        console.log(this.form.id);
        console.log(this.form.title)
        console.log(this.form.content)
      })
    },
    handleClose(done) { //关闭提醒
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        }).catch(_ => {});
    },
  }
}
</script>

<style lang="scss" scoped>
.pagination {
  display: flex;
  justify-content: center;
}

#message {
  margin: 0 auto;
  background-image: url("../../assets/img/landscape1.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}

.title {
  color: red;
}

.content {
  padding: 20px 0px;
}

#message  {
  .btn {
   padding-bottom: 20px;
  }
  .all {
    .date {
      color: deepskyblue;
      line-height: 45px;
      font-size: 14px;
    }
    .list {
      background-color: transparent;
      width: 100%;
      padding:10px;
      border-radius: 20px;
      margin: 10px 0;
      position: relative;
      transition: all .3s ease;
      .icon-untitled33 {
        font-size: 14px;
        margin-right: 4px;
      }
      .reply {
        position: absolute;
        right: 30px;
        bottom: 15px;
        color: red;
        cursor: pointer;
        transition: all .3s ease;
      }
      .comment {
        margin:-7px 0px;
        padding-bottom: 12px;
        font-size: 13px;
        color: green;
        i {
          margin-right: 4px;
        }
      }
    }
  }
}

#message .wrapper {
  background: rgba(255, 255, 255, 0.3);
  padding: 20px;
}

.list:hover {
  transform: scale(1.01);
}

#content1 {
  width: 100%;
  margin: 10px 0;
  border-style: solid;
  border-width: thin;
  border-color: rgb(220, 20, 60);
}

#title1 {
  width: 100%;
  margin: 10px 0;
  border-style: solid;
  border-width: thin;
  border-color: rgb(220, 20, 60);
}

</style>

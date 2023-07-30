<template>
  <div class = "q">
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: { //用户信息
        userName: null,
        userId: null
      }
    }
  },
  created() {
    this.getUserInfo()
  },
  methods: {
    getUserInfo() { //获取用户信息
      let userName = this.$cookies.get("cname")
      let userId = this.$cookies.get("cid")
      this.user.userName = userName
      this.user.userId = userId
    },
  }
}
</script>


<style lang="scss" scoped>
.q {
  width: 100%;
  height: 100%;
  background: url("../../assets/img/landscape6.jpg");
  background-size: cover;
  background-repeat: no-repeat;
}
</style>

<template>
  <div class="adjust">

    <video controls autoplay poster="../../assets/img/1.jpg">
      <source :src="require(`@/assets/video/`+ 5 +`.mp4`)" type="video/mp4">
    </video>

    <!--<img :src="require(`@/assets/img/`+ videoData.imageName +`.jpg`)">-->
  </div>
</template>

<script>
export default {
  data() {
    return {
      videoName: 0,
      videoData: {

      },
    }
  },
  created() {
    this.inital()
  },
  methods: {
    inital() {
      const map1 = new Map();
      let videoId = this.$route.query.videoId;

      this.$axios(`/api/video/${videoId}`).then(res => {
        this.videoData = { ...res.data.data}
      })
    },
  }
}
</script>
<style scoped>
.adjust {
  background: url("../../assets/img/landscape20.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  height: 712px;
}

video {
  opacity: 70%;
  width: 700px;
  height: 420px;
  margin-top: 150px;
  margin-left: 415px;
}

</style>

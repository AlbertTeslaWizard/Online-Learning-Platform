<template>
  <div id="a">
    <div class="wrap">
      <p id="b">
        {{messageData.title}}
      </p>
      <p id="c">
        {{messageData.content}}
      </p>
    </div>
  </div>
</template>

<script>
export default {
  //name: "messageshow"
  data() {
    return {
      Id: 0,
      messageData: {

      },
    }
  },
  created() {
    this.inital()
  },
  methods: {
    inital() {
      this.Id = this.$route.query.Id;
      let messageId = this.Id;
      console.log(this.Id);

      this.$axios(`/api/message/${messageId}`).then(res => {
        this.messageData = { ...res.data.data}
        console.log(this.messageData.title);
        console.log(res.data.data.title)
        console.log(res.data.data.content)
      })
    }
  }
}
</script>

<style scoped>
  #a {
    background-color: grey;
  }

  #b {
    text-align: center;
    font-size: 30px;
  }

  #c {
    text-indent: 2em;
    font-size: 20px;
  }

  .wrap{
    width: 90%;
    margin: auto;
    background-color: white;
    height: 100vh;
  }

</style>

<!--页脚部分-->
<template>
  <footer id="footer">
    <ul>
      <li><a href="javascript:;"></a></li>
      <li><a href="javascript:;"></a></li>
      <li><a href="javascript:;"></a></li>
      <li><a href="" target="_blank"></a></li>
      <li></li>
    </ul>
  </footer>
</template>

<script>
export default {
  name: "myFooter"
}
</script>

<style scoped>
#footer a {
  color: purple;
  font-size: 14px;
}
#footer {
  background: rgba(255, 255, 255, 0.3);
}
#footer ul {
  margin-top: 40px;
  border-top: 1px solid grey;
  display: flex;
  justify-content: center;
  height: 80px;
  line-height: 80px;
}
#footer ul li {
  color: white;
  font-size: 14px;
  margin-right: 20px;
}
</style>

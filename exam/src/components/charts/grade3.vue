<template>
  <div id="grade">
    <div ref="box" class="box"></div>
    <div class="notFound" v-if="isNull">
      <i class="iconfont icon-LC_icon_tips_fill"></i><span>该考生未参加考试</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "grade",
  data() {
    return {
      isNull: false, //原始数据
      tableDataX: [], //x轴数据 保存次数
      tableDataY: [], //y轴数据 保存分数
    }
  },
  mounted() {
    this.score();
  },
  methods: {
    score() {
      let studentId = this.$route.query.studentId
      this.$axios(`/api/score/${studentId}`).then(res => { //根据学生Id查询成绩
        console.log(res)
        if(res.data.code == 200) {
          let rootData = res.data.data
          console.log(rootData);
          rootData.forEach((element, index) => {
            this.tableDataX.push(`考试${index + 1}` + element.subject)
            this.tableDataY.push(element.etScore)
          });
          let boxDom = this.$refs["box"];
          let scoreCharts = this.$echarts.init(boxDom);
          let option = {
            xAxis: {
              type: "category",
              data: this.tableDataX,
              axisLabel: {
                interval: 0,
                formatter: function(params) {
                  var newParamsName = "";
                  var paramsNameNumber = params.length;
                  var provideNumber = 3; //一行显示几个字
                  var rowNumber = Math.ceil(paramsNameNumber / provideNumber);
                  if (paramsNameNumber > provideNumber) {
                    for (var p = 0; p < rowNumber; p++) {
                      var tempStr = "";
                      var start = p * provideNumber;
                      var end = start + provideNumber;
                      if (p == rowNumber - 1) {
                        tempStr = params.substring(start, paramsNameNumber);
                      } else {
                        tempStr = params.substring(start, end) + "\n";
                      }
                      newParamsName += tempStr;
                    }
                  } else {
                    newParamsName = params;
                  }
                  return newParamsName;
                }
              }
            },
              yAxis: {
                type: "value"
              },
              series: [
                {
                  data: this.tableDataY,
                  type: "line",
                  itemStyle: {
                    normal: {
                      label: {
                        show: true,
                        position: 'top'
                      },
                      color: 'rgb(220, 20, 60)'
                    }
                  },
                }
              ]
            };

          scoreCharts.setOption(option);
          scoreCharts.on("mouseover", params => {
            console.log(params.value);
          });
        }else {
          this.isNull = true
        }
      })
    }
  }
};
</script>

<style lang="scss" scoped>
#grade {
  position: relative;
  padding-left: 160px;
  padding-top: 30px;
  background: url("../../assets/img/landscape7.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  height: 680px;
  opacity: 90%;
  .box{
    width: 100%;
    height: 600px;
  }
  .notFound {
    position: absolute;
    top: 0px;
    left: 0px;
  }
}
</style>

<template>
  <div class="part" >
    <div class="box" ref="box" v-if="!isNull"></div>
    <div class="notFound" v-if="isNull">
      <i class="el-icon-warning-outline"></i><span>该考试目前还没有学生参加</span>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isNull: false, //是否有成绩标志位
      name: null,
      category: { //保存分数段
        '90分及以上': 0,
        '80-89分': 0,
        '70-79分': 0,
        '60-69分': 0,
        '60分以下': 0,
      }
    }
  },
  created() {
    this.getScoreInfo()
  },
  methods: {
    getScoreInfo() {
      let examCode = this.$route.query.examCode
      this.name = this.$route.query.source
      this.$axios(`/api/scores/${examCode}`).then(res => {
        let data = res.data.data
        if(data.length > 0) {
          let box = this.$refs['box']
          let charts = this.$echarts.init(box)
          data.forEach(element => {
            switch(element.etScore / 10) {
              case 10:
              case 9:
                this.category["90分及以上"]++
                break
              case 8:
                this.category['80-89分']++
                break
              case 7:
                this.category["70-79分"]++
                break
              case 6:
                this.category['60-69分']++
                break
              default:
                this.category['60分以下']++
            }
          });
          let option = {
              title : {
                  text: `${this.name}分数段图`,
                  textStyle: {
                    fontSize: 30,
                    color: "rgb(220, 20, 60)"
                  },
                  x:'center'
              },
              tooltip : {
                  trigger: 'item',
                  formatter: "{a}：{b} <br/> {c}人 ({d}%)"
              },
              legend: {
                  orient: 'vertical',
                  left: 'left',
                  data: ['90分及以上','80-89分','70-79分','60-69分','60分以下'],
                  textStyle: {
                    fontSize: 18,
                    color: ['rgb(220,20,60)', '#00FF00', '#FFA500', '#BA55D3', '#00BFFF']
                  }
              },
              color:['rgb(220,20,60)', '#00FF00', '#FFA500', '#BA55D3', '#00BFFF'],
            series : [
              {
                name: '分数段',
                type: 'pie',
                radius : '55%',
                center: ['50%', '35%'],
                data:[
                  {value:this.category['90分及以上'], name:'90分及以上'},
                  {value:this.category['80-89分'], name:'80-89分'},
                  {value:this.category['70-79分'], name:'70-79分'},
                  {value:this.category['60-69分'], name:'60-69分'},
                  {value:this.category['60分以下'], name:'60分以下'}
                ],
                itemStyle: {
                  emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                  }
                },
                label: {
                  normal: {
                    textStyle: {
                      fontSize: 18
                    }
                  }
                }
              }
            ]
          };
          charts.setOption(option)
        } else {
          this.isNull = true
        }
      })
    }
  },

}
</script>

<style lang="scss" scoped>
.part {
  .box {
    width: 800px;
    height: 800px;
    margin-left: 200px;
    margin-top: 50px;
    opacity: 100%;
  }
  .notFound {
    margin-left: 500px;
    margin-top: 300px;
    font-size: 30px;
    color: rgb(220, 20, 60);
  }
}
</style>



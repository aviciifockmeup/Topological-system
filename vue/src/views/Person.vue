<template>
  <div>
    <el-row>
      <el-col :span="12">
        <div id="main" style="width: 1500px; height: 600px"></div>
      </el-col>

    </el-row>


  </div>
</template>


<script>
import * as echarts from 'echarts'
import {forEach} from "core-js/stable/dom-collections";
import {looseEqual} from "element-ui";



export default {
  name: "Person",
  data() {
    return {
      personTable: [],
      total: 0,

    }
  },
  created() {
    this.load()
  },

  mounted() {
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);

    var nodes = [];
    var edges = [];

    this.request.get("/person/all").then(res=>{
      // console.log(res[0].name)
      res.forEach(item=>{


        // 这里可以通过加条件判断item中属性的值（如是否是关键路由器）来控制节点样式
        console.log(item)
        nodes.push({
          name:item.name,
          draggable: true

        });

        item.followNames.forEach(followName=>{
          edges.push({
            source: item.name,
            target: followName,
            label: {
              show: false
            },
            lineStyle: {
              curveness: 0.2
            }

          });

        })


      });

      console.log("nodes: " + nodes);
      console.log("edges: " + edges);
      console.log(edges[0])

      var option;

      option = {
        title: {
          text: 'Basic Graph'
        },
        tooltip: {},
        animationDurationUpdate: 1500,
        animationEasingUpdate: 'quinticInOut',
        series: [
          {
            type: 'graph',
            layout: 'force',
            symbolSize: 20,
            roam: true,
            label: {
              show: true
            },
            edgeSymbol: ['circle', 'arrow'],
            edgeSymbolSize: [4, 10],
            edgeLabel: {
              fontSize: 20
            },
            data: nodes,
            links: edges,
            // links: [
            //   {
            //     source: 0,
            //     target: 1,
            //     symbolSize: [5, 20],
            //     label: {
            //       show: false
            //     },
            //     lineStyle: {
            //       width: 5,
            //       curveness: 0.2
            //     }
            //   },
            //   {
            //     source: 0,
            //     target: 2,
            //     label: {
            //       show: false
            //     },
            //     lineStyle: {
            //       curveness: 0.2
            //     }
            //   },
            //   {
            //     source: 'Node 1',
            //     target: 'Node 3'
            //   },
            //   {
            //     source: 'Node 2',
            //     target: 'Node 3'
            //   },
            //   {
            //     source: 'Node 2',
            //     target: 'Node 4'
            //   },
            //   {
            //     source: 'Node 1',
            //     target: 'Node 4'
            //   }
            // ],
            lineStyle: {
              opacity: 0.9,
              width: 2,
              curveness: 0
            }
          }
        ]
      };

      option && myChart.setOption(option);


    })



  },


  methods: {
    load() {
      this.request.get("/person/all", {
        params: {
        }
      }).then(res => {
        // console.log(res)
        // console.log("res[0].name: " +  res[0].name)
        // console.log("res.length: " +  res.length)

        this.personTable = res
        this.total = res.length

      })
    },
  }
}



</script>
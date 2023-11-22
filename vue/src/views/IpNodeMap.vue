<template>
  <div>
    <el-row>
      <el-col :span="12">
        <div id="main" style="width: 2000px; height: 1000px"></div>
      </el-col>

    </el-row>


  </div>
</template>


<script>
import * as echarts from 'echarts'
import {forEach} from "core-js/stable/dom-collections";
import {looseEqual} from "element-ui";
import Device from '../assets/device.png';




export default {
  name: "IpNodeMap",
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

    this.request.get("/ip/all").then(res=>{

      res.forEach(item=>{

        // 这里可以通过加条件判断item中属性的值（如是否是关键路由器）来控制节点样式
        console.log(item)
        nodes.push({
          name:item.value,
          draggable: true,
          // color: "#03f",
          symbol: 'image://' + Device  // 可以使用不同的符号，如 'circle', 'rect', 'triangle', 'diamond' 等

        });

        item.connectedIPs.forEach(connectedIP=>{
          edges.push({
            source: item.value,
            target: connectedIP,
            label: {
              show: false
            },
            lineStyle: {
              opacity: 0.7,
              width: 1,
              curveness: 0,
              edgeLength: 0
              // color: "#014",
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
          text: 'IpNodeMap'
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

            force: {
              // initLayout: 'circular'
              // gravity: 0
              repulsion: 300,
              edgeLength: 5
            },

            lineStyle: {
              opacity: 0.7,
              width: 5,
              curveness: 0,
              edgeLength: 500
              // color: ''
            }

          }
        ]
      };

      option && myChart.setOption(option);


    })



  },


  methods: {
    load() {
      this.request.get("/ip/all", {
        params: {
        }
      }).then(res => {
        console.log(res)
        console.log("res[0].value: " +  res[0].value)
        console.log("res.length: " +  res.length)

        // this.personTable = res
        // this.total = res.length

      })
    },
  }
}



</script>
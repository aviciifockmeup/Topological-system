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



export default {
  name: "IpKeyNodeMap",
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

    this.request.get("/ip/allKeyNode").then(res=>{

      res.forEach(item=>{

        // 这里可以通过加条件判断item中属性的值（如是否是关键路由器）来控制节点样式
        console.log(item)

        var nodeColor;
        if(item.keyValue > 0.03 ){
          nodeColor = "#ff0000"
        }
        else if(0.02 < item.keyValue < 0.03){
          nodeColor = "#add8e6"
        }
        else{
          nodeColor = "#f08080"
        }
        nodes.push({
          name:item.value,
          draggable: true,
          symbol: 'react',  // 可以使用不同的符号，如 'circle', 'rect', 'triangle', 'diamond' 等
          itemStyle: {
            color: nodeColor,
          },

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
              // color: "#014",
            }

          });

        })

      });

      console.log("nodes: " + nodes);
      console.log("color: " + nodes[0].color);
      console.log("edges: " + edges);
      console.log(edges[0])

      var option;

      option = {
        title: {
          text: 'IpKeyNodeMap'
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
              width: 1,
              curveness: 0,
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
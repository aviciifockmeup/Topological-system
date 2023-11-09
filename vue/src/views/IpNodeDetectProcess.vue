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



export default {
  name: "IpNodeDetectProcess",
  data() {
    return {
      pathTable: [],
      total: 0,

    }
  },
  created() {
    this.load()
  },

  mounted() {

  },


  methods: {
    load() {
      this.request.get("/ip/detectProcess").then(res => {
        console.log(res)
        // console.log("res[0].value: " +  res[0].value)
        console.log("res.length: " +  res.length)

        this.pathTable = res
        this.total = res.length

        var chartDom = document.getElementById('main');
        var myChart = echarts.init(chartDom);
        var option;


        console.log("mounted pathTable: " + this.pathTable)



        const timeIntervals = this.pathTable


        const data = [];
        const edges = [];
        option = {
          series: [
            {
              type: 'graph',
              layout: 'force',
              animation: false,
              data: data,
              force: {
                // initLayout: 'circular'
                // gravity: 0
                repulsion: 100,
                edgeLength: 5
              },
              edges: edges,
              symbolSize: 15,
              roam: true,
              // edgeSymbol: ['circle', 'arrow'],
              edgeSymbolSize: [4, 10],
              edgeLabel: {
                fontSize: 20
              },

              draggable: true,

            }
          ]
        };


        let currentIndex = 0;


        // const nodes = interval.ipNodes.map(node => {
        //   let condition;
        //   // 根据节点的属性设置不同的条件
        //   if (node.property === 'value1') {
        //     condition = 'Condition 1';
        //   } else if (node.property === 'value2') {
        //     condition = 'Condition 2';
        //   } else {
        //     condition = 'Default Condition';
        //   }
        //
        //   return {
        //     id: node,
        //     condition: condition
        //   };
        // });



        setInterval(function () {
          if (currentIndex < timeIntervals.length) {
            const interval = timeIntervals[currentIndex];
            const nodes = interval.ipNodes.map(node =>
                ({ name: node,
                   label: {
                     show: true
                   },
                   symbol: 'react'}));
            const edges = interval.ipEdges.map(edge => {
              const [source, target] = edge.split(",");
              return {
                source,
                target
              };
            });

            // 删除timeIntervals里第一个元素
            timeIntervals.shift()

            console.log("ndoes:" + nodes)
            console.log("edges:" + edges)
            console.log()


            myChart.setOption({
              series: [
                {
                  roam: true,
                  data: nodes,
                  edges: edges
                }
              ]
            });
            // console.log('nodes: ' + data.length);
            // console.log('links: ' + data.length);
          }
        }, 1000);

        option && myChart.setOption(option);



      })
    },

  }
}



</script>
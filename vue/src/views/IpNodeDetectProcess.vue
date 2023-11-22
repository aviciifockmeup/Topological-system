<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入目的ip" suffix-icon="el-icon-search" v-model="target"></el-input>
      <el-input style="width: 200px" placeholder="请输入探测协议" suffix-icon="el-icon-search" class="ml-5" v-model="protocols"></el-input>
      <el-input style="width: 200px" placeholder="请输入线程方式" suffix-icon="el-icon-search" class="ml-5" v-model="model"></el-input>
      <el-input style="width: 200px" placeholder="请输入最大跳数" suffix-icon="el-icon-message" class="ml-5" v-model="maxttl"></el-input>
      <el-input style="width: 200px" placeholder="请输入最大等待时间" suffix-icon="el-icon-position" class="ml-5" v-model="timeout"></el-input>
      <el-button class="ml-5" type="primary" @click="start">开始探测</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>


    <el-table :data="taskData" border stripe :header-cell-class-name="'headerBg'">
      <el-table-column prop="taskId" label="taskId" width="80"></el-table-column>
      <el-table-column prop="target" label="target" width="80"></el-table-column>
      <el-table-column prop="publishTasksCount" label="publishTasksCount"></el-table-column>
      <el-table-column prop="pendingTasksCount" label="pendingTasksCount"></el-table-column>
      <el-table-column prop="submittedTasksCount" label="submittedTasksCount"></el-table-column>
      <el-table-column prop="processingTasksCount" label="processingTasksCount"></el-table-column>
      <el-table-column prop="nodeId" label="nodeId"></el-table-column>
      <el-table-column prop="ipPort" label="ipPort"></el-table-column>
      <el-table-column prop="nodeType" label="nodeType"></el-table-column>
      <el-table-column prop="nodeStatus" label="nodeStatus"></el-table-column>
      <el-table-column prop="nodeInfo" label="nodeInfo"></el-table-column>
      <el-table-column prop="nodeTask" label="nodeTask"></el-table-column>

    </el-table>


    <el-row>
      <el-col :span="12">
        <div id="main" style="width: 2000px; height: 1000px"></div>
      </el-col>

    </el-row>


  </div>
</template>


<script>
import * as echarts from 'echarts'
import _ from 'lodash';



export default {
  name: "IpNodeDetectProcess",
  data() {
    return {
      taskData: [],
      ipData: [],
      pathTable: [],
      total: 0,

      target: "",
      protocols: "",
      model: "",
      maxttl: "",
      timeout: "",

      requested_data: ""

    }
  },
  created() {
    // this.load()
  },

  mounted() {

  },


  methods: {

    start(){
      console.log("Start from here")
      this.request.get("/ip/startDetection", {
        params: {
          target: this.target,
          protocols: this.protocols,
          model: this.model,
          maxttl: this.maxttl,
          timeout: this.timeout
        },
      }).then(res => {

        console.log(res)
        // console.log("here")
        // console.log("res: " + res.protocols)
        // this.requested_data = res

        // 再循环前初始化echarts
        var chartDom = document.getElementById('main');
        var myChart = echarts.init(chartDom);
        var option;

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


        this.taskInfoTimerId = setInterval(() => {
          this.getTaskInfo();
        }, 1000);


        this.detectionTimerId = setInterval(() =>{
          this.getDetectionResult()
        }, 2000)
        option && myChart.setOption(option);

        // if (this.taskData.processingTasksCount === 0) {
        //   console.log("job done!")
        //   clearInterval(this.taskInfoTimerId);
        // }

      })

    },

    getTaskInfo(){
      this.request.get("task/getTaskNodeInfo").then(res => {
        // console.log(res)
        // console.log(1)
        this.taskData = res
      })

    },



    getDetectionResult(){
      this.request.get("ip/getDetectionResult").then(res => {
        console.log("Detection result: " + res)
        console.log(2)

        // 使用深度相等性检查，使用类似Lodash的库执行深度比较，而不是直接!==。（使用if(this.ipData !== res)进行比较，始终为真，因为this.ipData和res是不同的实例，即使它们内容相同）
        if(!_.isEqual(this.ipData, res)){

          this.ipData = res

          var chartDom = document.getElementById('main');
          var myChart = echarts.init(chartDom);
          var option;

          console.log("mounted pathTable: " + this.ipData)

          const nodes = this.ipData.ipNodeValueList.map(node =>
              ({ name: node,
                label: {
                  show: true
                },
                symbol: 'react'}));

          const edges = []
          this.ipData.ipEdgeValueList.forEach(edgeStr => {
            // 解析JSON字符串
            const edgeObj = JSON.parse(edgeStr)
            const source = edgeObj.Source
            const target = edgeObj.Target

            edges.push({source, target})

          });

          console.log("ndoes:" + nodes)
          console.log("edges:" + edges)

          myChart.setOption({
            series: [
              {
                roam: true,
                data: nodes,
                edges: edges
              }
            ]
          });

        }
        else {
          console.log("no change yet.")
        }




      })
          // .catch(error => {
          //   console.error("Error fetching detection result: ", error);
          //   // 处理获取探测结果失败的情况
          // });
    },


    reset() {
      this.target = ""
      this.protocols = ""
      this.model = ""
      this.maxttl = ""
      this.timeout = ""

    },




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
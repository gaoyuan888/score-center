<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/default.jsp" %>
<div id="app">
    <el-container>
        <el-main v-for="record in records" v-model="records"
                 v-bind:class="{red: record.athlete==2, blue: record.athlete==1 }">
            <h1>{{record.score}}</h1>
        </el-main>
    </el-container>
</div>

<script>
    var app1 = new Vue({
        el: '#app',
        data: {
            records: {},
        },
        created() {
            this.getData();
            setInterval(this.getDataTimer, 500);
        },
        methods: {
            //定時器，定時刷新数据
            getDataTimer: function () {
                this.getData();
            },
            getData() {
                var _this = this;
                $.ajax({
                    //几个参数需要注意一下
                    url: "/resultRcord",//url
                    type: "get",//方法类型
                    data: "",
                    dataType: "json",//预期服务器返回的数据类型
                    success: function (res) {
                        if (res.flag == true) {
                            _this.records = res.data;
                        } else {
                            alert("获取失败！");
                        }
                    },
                    error: function () {
                        alert("异常！");
                    }
                });
            },
            handleSizeChange(val) {

            },
            handleCurrentChange(val) {
            }
        }
    })
</script>

<style scoped>
    .wrapper {
        text-align: center;
        width: 60%;
        margin: 250px auto;
    }

    .row-bg {
        padding: 0px 0;
        background-color: #f9fafc;
    }

    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        font-size: 200px;
    }
    .red {
        background-color: #f10e2c;
        text-align: center;
        font-size: 200px;
    }
    .blue {
        background-color: #1a2ef1;
        text-align: center;
        font-size: 200px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }
</style>
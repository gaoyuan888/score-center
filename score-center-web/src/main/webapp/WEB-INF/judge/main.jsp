<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/default.jsp" %>
<div id="app">
    <el-container>
        <el-header>Header</el-header>
        <el-main>
            <el-container>
                <el-main v-for="record in records" v-model="records">
                    姓名：{{record.athleteName}}
                </el-main>
            </el-container>
        </el-main>
        <el-footer>Footer</el-footer>
    </el-container>
</div>

<script>
    var app1 = new Vue({
        el: '#app',
        data: {
            records: {}
        },
        created() {
            this.getData();
        },
        methods: {
            onSubmit(athlete, score, referee) {
                var _this = this;
                //将裁判对运动员的打分传到后台
                $.ajax({
                    type: "POST",//方法类型
                    url: "/record",//url
                    data: this.record,
                    dataType: "json",//预期服务器返回的数据类型
                    success: function (res) {
                        if (res.flag == true) {
                            console.info(_this.record);
                            console.info('success');
                        } else {
                            alert("客戶端信息为空");
                        }

                    },
                    error: function () {
                        alert("异常！");
                    }
                });
            },
            //时间格式化
            dateFormat(row, column) {
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

<style>
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
        line-height: 160px;
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
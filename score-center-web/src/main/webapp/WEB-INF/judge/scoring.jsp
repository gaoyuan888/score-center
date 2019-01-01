<%@ include file="/WEB-INF/common/default.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="app">
    <el-container>
        蓝
        <el-main width="50%">
            <el-row type="flex" class="row-bg">
                <el-col :span="6">
                    <el-button circle @click="onSubmit(1,1)">1分</el-button>
                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg" justify="center">
                <el-col :span="6">
                    <el-button type="primary" circle @click="onSubmit(1,2)">2分</el-button>
                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg" justify="end">
                <el-col :span="6">
                    <el-button type="success" circle @click="onSubmit(1,3)">3分</el-button>
                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg" justify="end">
                <el-col :span="6">
                    <el-button type="info" circle @click="onSubmit(1,4)">4分</el-button>
                </el-col>
            </el-row>
        </el-main>
        红
        <el-main>
            <el-row type="flex" class="row-bg" justify="end">
                <el-col :span="6">
                    <el-button type="info" circle @click="onSubmit(2,1)">1分</el-button>
                </el-col>
            </el-row>

            <el-row type="flex" class="row-bg" justify="center">
                <el-col :span="6">
                    <el-button type="success" circle @click="onSubmit(2,2)">2分</el-button>
                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg">
                <el-col :span="6">
                    <el-button type="primary" circle @click="onSubmit(2,3)">3分</el-button>
                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg">
                <el-col :span="6">
                    <el-button circle @click="onSubmit(2,4)">4分</el-button>
                </el-col>
            </el-row>
        </el-main>
    </el-container>
</div>


<script>
    var app1 = new Vue({
        el: '#app',
        data: {
            record: {
                athlete: '',
                score: '',
                referee: ''
            }
        },
        created() {
            this.getData();
        },
        methods: {
            onSubmit(athlete, score) {
                this.record.athlete = athlete;
                this.record.score = score;
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
                    url: "/referee",//url
                    type: "GET",//方法类型
                    data: "",
                    dataType: "json",//预期服务器返回的数据类型
                    success: function (res) {
                        if (res.flag == true) {
                            _this.record.referee = res.data;
                        } else {
                            alert("客戶端信息为空");
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
    .el-row {
        margin-bottom: 20px;
    }

    .el-col {
        border-radius: 4px;
    }

    .bg-purple-dark {
        background: #99a9bf;
    }

    .bg-purple {
        background: #d3dce6;
    }

    .bg-purple-light {
        background: #e5e9f2;
    }

    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }

    .row-bg {
        padding: 10px 0;
        background-color: #f9fafc;
    }

</style>
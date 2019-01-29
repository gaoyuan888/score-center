<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/default.jsp" %>
<div id="app">
    <span style="font-size: 35px">剩余时间:{{timeDesc}}</span>
    <el-container>
        <el-main v-for="record in records" v-model="records"
                 v-bind:class="{red: record.athlete==2, blue: record.athlete==1 }">
            <h4 style="margin-bottom: 20px;">{{record.score}}</h4>
            <span style="font-size: 40px">犯规次数：{{record.foulNum}}</span>
        </el-main>
    </el-container>
    <el-button @click="resetTime" type="primary">开始计时
    </el-button>
    <el-button @click="suspendTime" type="primary">暂停计时
    </el-button>
    <el-button @click="goTime" type="primary">继续计时
    </el-button>

    <el-dialog
            title="倒计时输入"
            :visible.sync="dialogVisible"
            width="30%"
            :before-close="handleClose">
        <el-input v-model="secondes" placeholder="输入倒计时(S)"></el-input>
        <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="startTiming">开始计时</el-button>
        </span>
    </el-dialog>


</div>

<script>
    var app1 = new Vue({
        el: '#app',
        data: {
            records: {},
            buttonName: "倒计时",
            isDisabled: false,
            secondes: 10,
            dialogVisible: false,
            timeDesc: "_",
            continu: true,
            endTime: '',
            stopTime: false
        },
        created() {
            this.getData();
            setInterval(this.getDataTimer, 500);
        },
        methods: {
            handleClose: function () {
            },
            resetTime: function () {
                this.endTime = ''
                // 显示对话框
                this.dialogVisible = true
                //停掉倒计时
                this.stopTime = true
                //当前时间
            },
            goTime: function () {
                this.continu = true
            },
            suspendTime: function () {
                this.continu = false
            },

            startTiming: function () {
                this.dialogVisible = false
                this.continu = true
                this.stopTime = false
                this.countTime()
            },

            countTime: function () {
                if (this.stopTime) {
                    return
                }
                if (!this.continu) {
                    this.endTime = this.endTime + 1000
                    setTimeout(this.countTime, 1000);
                    return
                }
                //获取当前时间
                var date = new Date();
                var now = date.getTime();
                //设置截止时间
                // var endDate = new Date("2019-10-22 23:23:23");
                if (!this.endTime) {
                    this.endTime = date.getTime() + 1000 * this.secondes;
                }
                //时间差
                var leftTime = this.endTime - now;
                if (leftTime < 0) {
                    this.endTime = ""
                    return
                }
                //定义变量 d,h,m,s保存倒计时的时间
                if (leftTime >= 0) {
                    d = Math.floor(leftTime / 1000 / 60 / 60 / 24);
                    this.h = Math.floor(leftTime / 1000 / 60 / 60 % 24);
                    this.m = Math.floor(leftTime / 1000 / 60 % 60);
                    this.s = Math.floor(leftTime / 1000 % 60);
                }
                this.timeDesc = this.m + ":" + this.s
                this.secondes = this.m * 60 + this.s
                // console.log(this.h+":"+this.m+":"+this.s);
                //递归每秒调用countTime方法，显示动态时间效果
                setTimeout(this.countTime, 1000);
            },

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
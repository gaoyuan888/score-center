<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/default.jsp" %>
<div id="app">
    <el-container>
        <el-header><h1 id=mytime>{{timerObj.str}}</h1></el-header>
        <el-main>
            <el-container>
                <el-main v-for="record in records" v-model="records">
                    姓名：{{record.athleteName}}</br>
                    得分：{{record.score}}</br>
                    犯规次数：{{record.foulNum}}</br>
                    <el-button type="primary" circle @click="onFoul(record.athlete)">犯规</el-button>
                    <el-button type="primary" circle @click="onAddBaseScore(record.athlete)">+1</el-button>
                    <el-button type="primary" circle @click="onReduceBaseScore(record.athlete)">-1</el-button>
                </el-main>
            </el-container>
        </el-main>
        <el-footer>
            <button id="stop" name="button" @click="stop">停止计时</button>
            <button id="start" name="button" @click="start">开始计时</button>
            <button id="reset" name="button" @click="reset">重置时间</button>
            <button id="resetGame" name="button" @click="resetGame">重置比赛</button>
        </el-footer>
    </el-container>
</div>

<script>
    var app1 = new Vue({
        el: '#app',
        data: {
            records: {},
            content: '倒计时开始',
            clock: '',
            totalTime: 60,
            timerObj: {
                h: 0,//定义时，分，秒，毫秒并初始化为0；
                m: 0,
                ms: 0,
                s: 0,
                time: 0,
                str: '',
                mytime: ''
            }
        },
        created() {
            this.getData();
            setInterval(this.getDataTimer, 1000);
        },
        methods: {
            timer() {   //定义计时函数
                this.timerObj.ms = this.timerObj.ms + 50;         //毫秒
                if (this.timerObj.ms >= 1000) {
                    this.timerObj.ms = 0;
                    this.timerObj.s = this.timerObj.s + 1;         //秒
                }
                if (this.timerObj.s >= 60) {
                    this.timerObj.s = 0;
                    this.timerObj.m = this.timerObj.m + 1;        //分钟
                }
                if (this.timerObj.m >= 60) {
                    this.timerObj.m = 0;
                    this.timerObj.h = this.timerObj.h + 1;        //小时
                }
                this.timerObj.str = this.toDub(this.timerObj.h) + ":" + this.toDub(this.timerObj.m) + ":" + this.toDub(this.timerObj.s) + ""/*+this.toDubms(this.ms)+"毫秒"*/;
            },

            reset() {  //重置
                clearInterval(this.timerObj.time);
                this.timerObj.h = 0;
                this.timerObj.m = 0;
                this.timerObj.ms = 0;
                this.timerObj.s = 0;
                this.timerObj.str = "00:00:00";
            },

            start() {  //开始
                this.timerObj.time = setInterval(this.timer, 50);
            },

            stop() {  //暂停
                clearInterval(this.timerObj.time);
            },
            toDub(n) {  //补0操作
                if (n < 10) {
                    return "0" + n;
                }
                else {
                    return "" + n;
                }
            },

            toDubms(n) {  //给毫秒补0操作
                if (n < 10) {
                    return "00" + n;
                }
                else {
                    return "0" + n;
                }

            },
            //定時器，定時刷新数据
            getDataTimer: function () {
                this.getData();
            },
            onReduceBaseScore(athlete) {
                //将裁判对运动员的打分传到后台
                $.ajax({
                    type: "POST",//方法类型
                    url: "/reduceBaseScore",//url
                    data: {'athlete': athlete},
                    dataType: "json",//预期服务器返回的数据类型
                    success: function (res) {
                        if (res.flag == true) {
                            // _this.records = res.data;
                        } else {
                            alert("客戶端信息为空");
                        }

                    },
                    error: function () {
                        alert("异常！");
                    }
                });
            },
            onAddBaseScore(athlete) {
                //将裁判对运动员的打分传到后台
                $.ajax({
                    type: "POST",//方法类型
                    url: "/addBaseScore",//url
                    data: {'athlete': athlete},
                    dataType: "json",//预期服务器返回的数据类型
                    success: function (res) {
                        if (res.flag == true) {
                            // _this.records = res.data;
                        } else {
                            alert("客戶端信息为空");
                        }

                    },
                    error: function () {
                        alert("异常！");
                    }
                });
            },
            onFoul(athlete) {
                //将裁判对运动员的打分传到后台
                $.ajax({
                    type: "POST",//方法类型
                    url: "/foul",//url
                    data: {'athlete': athlete},
                    dataType: "json",//预期服务器返回的数据类型
                    success: function (res) {
                        if (res.flag == true) {
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
            resetGame() {
                $.ajax({
                    //几个参数需要注意一下
                    url: "/resetGame",//url
                    type: "get",//方法类型
                    data: "",
                    dataType: "json",//预期服务器返回的数据类型
                    success: function (res) {
                        if (res.flag == true) {
                            alert("已重置比赛!");
                        } else {
                            alert("重置失敗");
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


    /*#mytime{*/
    /*background: #bbb;*/
    /*color: #fff;*/
    /*display: block;*/
    /*}*/
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
        /*line-height: 160px;*/
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
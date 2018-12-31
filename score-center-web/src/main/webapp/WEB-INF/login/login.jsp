<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<template>
    <el-form :model="userInfo"  label-position="left" label-width="0px"
             class="login-container">
        <h3 class="title">系统登录</h3>
        <el-form-item prop="account">
            <el-input type="text" v-model="userInfo.refereeName" auto-complete="off" placeholder="裁判员姓名"></el-input>
        </el-form-item>
        <el-form-item prop="checkPass">
            <el-input type="password" v-model="userInfo.athleteNameRed" auto-complete="off" placeholder="红方姓名"></el-input>
        </el-form-item>
        <el-form-item prop="checkPass">
            <el-input type="password" v-model="userInfo.athleteNameBlue" auto-complete="off" placeholder="蓝方姓名"></el-input>
        </el-form-item>
        <el-form-item style="width:100%;">
            <el-button type="primary" style="width:100%;" @click="submit" :loading="logining">登录
            </el-button>
        </el-form-item>
    </el-form>
</template>

<script>
    var app1 = new Vue({
        el: '#app',
        data: {
            userInfo:{}
        },
        methods: {
            submit() {

            },
            handleSubmit2(ev) {
                sessionStorage.setItem('user', JSON.stringify(user));
            }
        }

    })

</script>

<style lang="scss" scoped>
    .login-container {
        /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
        -webkit-border-radius: 5px;
        border-radius: 5px;
        -moz-border-radius: 5px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    .title {
        margin: 0px auto 40px auto;
        text-align: center;
        color: #505458;
    }
    .remember {
        margin: 0px 0px 35px 0px;
    }
    }
</style>

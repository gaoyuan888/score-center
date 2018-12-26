package com.gaoyua.score.controller;

import com.gaoyua.score.common.ResultJson;
import com.gaoyua.score.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author yaoyizhou
 * @date 2018/12/24 20:38
 * @desc
 */
@Controller
public class TestVue {

    @RequestMapping(value = "/login")
    @ResponseBody
    public ResultJson login(@RequestBody User user) {
        // 查询商品列表
        ResultJson json = new ResultJson();
        if (user != null
                && StringUtils.isNotBlank(user.getPassWord())
                && StringUtils.isNotBlank(user.getUserName())
                && user.getPassWord().equals("123456")
                && user.getUserName().equals("yaoyizhou1")) {
            json.setData(true);
            json.setFlag(true);
        }
        if (user != null
                && StringUtils.isNotBlank(user.getPassWord())
                && StringUtils.isNotBlank(user.getUserName())
                && user.getPassWord().equals("123456")
                && user.getUserName().equals("liguofan2")) {
            json.setData(true);
            json.setFlag(true);
        }
        return json;
    }

    @RequestMapping(value = "/user/list")
    @ResponseBody
    public ResultJson index() {
        ResultJson json = new ResultJson();
        json.setFlag(true);
        json.setData("fdas");
        return json;
    }
}

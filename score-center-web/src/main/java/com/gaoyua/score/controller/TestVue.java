package com.gaoyua.score.controller;

import com.gaoyua.score.common.ResultJson;
import com.gaoyua.score.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/login")
    @ResponseBody
    public ResultJson login(Model model, User user) {
        // 查询商品列表
        Map<String, String> result = new HashMap<>();
        // 把查询结果传递给页面
        result.put("user", "admin");
        result.put("password", "123456");
        ResultJson json = new ResultJson();
        json.setData(result);
        json.setFlag(true);
        // 返回结果
        return json;
    }

//    @RequestMapping(value = "/index")
//    public String index(HttpServletRequest request, HttpServletResponse response) {
//        return "index";
//    }
}

package com.gaoyua.score.controller;

import com.gaoyua.score.common.ResultJson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
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
    public ResultJson login(Model model){
        // 查询商品列表
        Map<String,String> result=new HashMap<>();
        // 把查询结果传递给页面
        result.put("user","admin");
        result.put("password","123456");
        ResultJson json=new ResultJson();
        json.setData(result);
        // 返回结果
        return json;
    }
}

package com.gaoyua.score.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能描述:
 *
 * @author yaoyizhou
 * @date 2018/12/31 9:37
 * @desc
 */
@Controller
public class ScoreCotroller {

    @RequestMapping("/")
    public String index(){
        return "scoring";
    }
}

package com.gaoyua.score.controller;

import com.gaoyua.score.common.ResultJson;
import com.gaoyua.score.domain.Record;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String index() {
        return "scoring";
    }

    @RequestMapping("/record")
    @ResponseBody
    public ResultJson<Boolean> record(Record record) {
        ResultJson result = new ResultJson();
        result.setData("0000");
        result.setFlag(true);
        return result;
    }
}

package com.gaoyua.score.controller;

import com.gaoyua.score.common.ResultJson;
import com.gaoyua.score.common.constant.RecordList;
import com.gaoyua.score.domain.Record;
import com.gaoyua.score.service.ScoreService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;

/**
 * 功能描述:
 *
 * @author yaoyizhou
 * @date 2018/12/31 9:37
 * @desc
 */
@Controller
public class ScoreCotroller {

    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/")
    public String index() {
        return "scoring";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/referee")
    @ResponseBody
    public ResultJson<String> referee(Model model) {
        ResultJson result = new ResultJson();
        String referee = RecordList.iterator.next();
        result.setFlag(true);
        result.setData(referee);
        return result;
    }

    @RequestMapping("/resultRcord")
    @ResponseBody
    public ResultJson<List<Record>> resultRcord() {
        ResultJson result = new ResultJson();
        List<Record> record = scoreService.getRecord();
        result.setData(record);
        result.setFlag(true);
        return result;
    }

    @RequestMapping("/record")
    @ResponseBody
    public ResultJson<Boolean> record(Record record) {
        ResultJson result = new ResultJson();
        scoreService.storeRecordInfo(record);
        result.setData("0000");
        result.setFlag(true);
        return result;
    }

}

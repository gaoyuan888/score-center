package com.gaoyua.score.controller;

import com.gaoyua.score.common.ResultJson;
import com.gaoyua.score.common.constant.RecordList;
import com.gaoyua.score.domain.Record;
import com.gaoyua.score.service.ScoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
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
        initFun();
        return "scoring";
    }

    /**
     * 系统初始化定时器
     * 初始化迭代器
     */
    private void initFun() {
        scoreService.getResultScheduled();
        if (RecordList.iterator == null) {
            RecordList.iterator = RecordList.refereeList.iterator();
        }
    }

    @RequestMapping("/clear")
    public void resetReferee() {
        //清除登陆信息
        RecordList.iterator = RecordList.refereeList.iterator();
        //清除da打分信息
        RecordList.recordList.clear();
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }

    @RequestMapping("/error")
    public String error() {
        return "error";
    }

    @RequestMapping("/referee")
    @ResponseBody
    public ResultJson<String> referee(String referee) {
        //防止重新刷新页面
        ResultJson result = new ResultJson();
        if (StringUtils.isBlank(referee)) {
            if (RecordList.iterator.hasNext()) {
                referee = RecordList.iterator.next();
            } else {
                result.setFlag(false);
                result.setData("已有3名裁判员处于在线状态");
                return result;
            }
        }
        result.setFlag(true);
        result.setData(referee);
        return result;
    }

    @RequestMapping("/resultRcord")
    @ResponseBody
    public ResultJson<List<Record>> resultRcord() {
        ResultJson result = new ResultJson();
        List<Record> record = RecordList.result;
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


    @RequestMapping("/foul")
    @ResponseBody
    public ResultJson<Boolean> foul(Integer athlete) {
        ResultJson result = new ResultJson();
        Record r1 = new Record(athlete, 0, 1, new Date(), 1, 0);
        Record r2 = new Record(athlete == 1 ? 2 : 1, 0, 0, new Date(), 1, 0);
        scoreService.storeRecordInfo(r1);
        scoreService.storeRecordInfo(r2);
//        List<Record> res = RecordList.result;
        result.setData(true);
        result.setFlag(true);
        return result;
    }

    @RequestMapping("/addBaseScore")
    @ResponseBody
    public ResultJson<Boolean> addBaseScore(Integer athlete) {
        ResultJson result = new ResultJson();
        Record r1 = new Record(athlete, 0, 0, new Date(), 1, 1);
        scoreService.storeRecordInfo(r1);
        result.setData(true);
        result.setFlag(true);
        return result;
    }

    @RequestMapping("/reduceBaseScore")
    @ResponseBody
    public ResultJson<Boolean> reduceBaseScore(Integer athlete) {
        ResultJson result = new ResultJson();
        Record r1 = new Record(athlete, 0, 0, new Date(), 1, -1);
        scoreService.storeRecordInfo(r1);
        List<Record> res = RecordList.result;
        for (Record re : res) {
            if (athlete.equals(re.getAthlete())) {
                re.setBaseScore(re.getBaseScore() - 1);
            }
        }
        result.setData(true);
        result.setFlag(true);
        return result;
    }
}

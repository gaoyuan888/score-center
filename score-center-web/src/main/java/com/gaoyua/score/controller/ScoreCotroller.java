package com.gaoyua.score.controller;

import com.gaoyua.score.common.ResultJson;
import com.gaoyua.score.common.constant.RecordList;
import com.gaoyua.score.domain.Record;
import com.gaoyua.score.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
        scoreService.getResultScheduled();
        return "scoring";
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }

    @RequestMapping("/referee")
    @ResponseBody
    public ResultJson<String> referee() {
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
        Record r1 = new Record(athlete, 0, 1, new Date(), 1);
        scoreService.storeRecordInfo(r1);
//        List<Record> res = RecordList.result;
        result.setData(true);
        result.setFlag(true);
        return result;
    }

    @RequestMapping("/addScore")
    @ResponseBody
    public ResultJson<Boolean> addScore(Integer athlete) {
        ResultJson result = new ResultJson();
        List<Record> res = RecordList.result;
        for (Record re : res) {
            if (athlete.equals(re.getAthlete())) {
                re.setBaseScore(re.getBaseScore() + 1);
            }
        }
        result.setData(true);
        result.setFlag(true);
        return result;
    }

    @RequestMapping("/reduceScore")
    @ResponseBody
    public ResultJson<Boolean> reduceScore(Integer athlete) {
        ResultJson result = new ResultJson();
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

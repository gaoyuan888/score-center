package com.gaoyua.score.controller;

import com.gaoyua.score.common.ResultJson;
import com.gaoyua.score.common.constant.RecordList;
import com.gaoyua.score.domain.Record;
import com.gaoyua.score.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/")
    public String index(Model model) {
        String referee = RecordList.refereeList.get(0);
        RecordList.refereeList.remove(0);
        model.addAttribute("referee", referee);
        return "scoring";
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

package com.gaoyua.score.service.impl;

import com.gaoyua.score.common.Enum.AthleteEnum;
import com.gaoyua.score.common.Enum.RefereeEnum;
import com.gaoyua.score.common.constant.RecordList;
import com.gaoyua.score.domain.Record;
import com.gaoyua.score.service.ScoreService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:
 *
 * @author yaoyizhou
 * @date 2018/12/31 15:46
 * @desc
 */
@Service("scoreService")
public class ScoreServiceImpl implements ScoreService {
    @Override
    public void storeRecordInfo(Record record) {
        record.setCreated(new Date());
        //新插入数据默认标记
        record.setFlag(1);
        RecordList.recordList.add(record);
    }

    @Override
    public List<Record> getRecord() {
        //所有打分记录
        ArrayList<Record> list = RecordList.recordList;
        //先按照红、蓝两个运动员分组
        List<Record> red = getRecordGroupByAthLate(list, AthleteEnum.ATHLETERED.getType());
        List<Record> blue = getRecordGroupByAthLate(list, AthleteEnum.ATHLETEBLEUE.getType());
        //分别针对红、蓝运动员
        // 1.按照裁判员分组
        // 2.并計算各組有效得分
        Record redEffectScore = getEffectScoreGroupByAthlateAndReferee(red);
        Record blueEffectScore = getEffectScoreGroupByAthlateAndReferee(blue);
        //获取犯规次数
        Record redResult = getScoreResult(redEffectScore, red);
        Record blueResult = getScoreResult(blueEffectScore, blue);
        List<Record> result = new ArrayList<>();
        result.add(redResult);
        result.add(blueResult);
        return result;
    }

    private Record getScoreResult(Record effectScore, List<Record> recordList) {
        if (recordList != null && recordList.size() > 0) {
            for (Record record : recordList) {
                effectScore.setFoulNum(effectScore.getFoulNum() + record.getFoulNum());
            }
        }
        return effectScore;
    }


    //根据运动员标记将打分记录分组
    private List<Record> getRecordGroupByAthLate(List<Record> list, Integer athlateFlag) {
        List<Record> result = new ArrayList<>();
        for (Record record : list) {
            if (record.getAthlete().equals(athlateFlag)) {
                result.add(record);
            }
        }
        return result;
    }

    private Record getEffectScoreGroupByAthlateAndReferee(List<Record> ahtlet) {

        Record result = new Record();
        //分别将红、蓝两组队员按照三个裁判分组
        List<Record> one = new ArrayList<>();
        List<Record> two = new ArrayList<>();
        List<Record> three = new ArrayList<>();

        assemByReferee(one, two, three, ahtlet);
        //遍历1,2,3号裁判的评分
        Record avgOne = getEffectScore(one, two, three);
        Record avgTwo = getEffectScore(two, three, one);
        Record avgThree = getEffectScore(three, two, one);

        result.setScore(avgOne.getScore() + avgTwo.getScore() + avgThree.getScore());
        result.setAthlete(avgOne.getAthlete());
        return result;
    }

    //对红方或者蓝方按照裁判打分分组
    private void assemByReferee(List<Record> one, List<Record> two, List<Record> three, List<Record> ahtlet) {
        for (Record record : ahtlet) {
            if (record.getReferee().equals(RefereeEnum.REREREE_ONE.getDesc())) {
                one.add(record);
            }
            if (record.getReferee().equals(RefereeEnum.REREREE_TWO.getDesc())) {
                two.add(record);
            }
            if (record.getReferee().equals(RefereeEnum.REREREE_THREE.getDesc())) {
                three.add(record);
            }
        }
    }

    private Record getEffectScore(List<Record> one, List<Record> two, List<Record> three) {
        Record result = new Record();
        Integer flag = 0;
        for (Record r1 : one) {
            if (r1.getFlag().equals(2)) {
                continue;
            }
            //为有效得分打标记
            setFlag4TargetRecord(r1,two,flag);
            setFlag4TargetRecord(r1,three,flag);
            if (flag >= 2) {
                result.setScore(result.getScore() + r1.getScore());
                result.setAthlete(r1.getAthlete());
            }
        }

        return result;
    }

    private void setFlag4TargetRecord(Record according, List<Record> target, Integer flag) {
        Date date = according.getCreated();
        for (Record r3 : target) {
            if (r3.getFlag().equals(2)) {
                continue;
            }
            if (r3.getCreated().before(DateUtils.addSeconds(date, 2))
                    && r3.getCreated().after(DateUtils.addSeconds(date, -2))
                    && r3.getScore().equals(according.getScore())) {
                r3.setFlag(2);
                according.setFlag(2);
                flag++;
            }
        }
    }


}

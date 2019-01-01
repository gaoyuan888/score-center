package com.gaoyua.score.service;

import com.gaoyua.score.domain.Record;

import java.util.List;

/**
 * 功能描述:
 *
 * @author yaoyizhou
 * @date 2018/12/31 15:46
 * @desc
 */
public interface ScoreService {
    /**
     * 记录打分信息
     *
     * @param record
     */
    void storeRecordInfo(Record record);

    /**
     * 计算打分信息,核心算法，定时执行一下，每次从job中
     */
    List<Record> getRecord();
}

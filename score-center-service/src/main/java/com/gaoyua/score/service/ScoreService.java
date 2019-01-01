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
     * 获取某一个运动员的打分记录
     *
     * @param athlete
     * @return
     */
    Record getRecord(Integer athlete);

    /**
     * 计算打分信息,核心算法，定时执行一下，每次从job中
     */
    List<Record> getRecord();

    /**
     * 启动一个定时器，定时计算结果
     */

    void getResultScheduled();

}

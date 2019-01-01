package com.gaoyua.score.common.constant;

import com.gaoyua.score.domain.Record;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

/**
 * 功能描述:
 *
 * @author yaoyizhou
 * @date 2018/12/31 15:42
 * @desc
 */
public class RecordList {
    /**
     * 用一个list记录打分信息
     */
    public static ArrayList<Record> recordList = new ArrayList<>();

    /**
     * 记录裁判信息，初始化分配3个裁判
     */
    public static volatile Iterator<String> iterator = new ArrayList<>(Arrays.asList("refereeOne", "refereeTwo", "refereeThree")).iterator();

    /**
     * 两个运动员最终得分结果
     */
    public static ArrayList<Record> result = new ArrayList<Record>() {
        {
            add(new Record(1, 0, 0, new Date(), 1));
            add(new Record(2, 0, 0, new Date(), 1));
        }
    };

    /**
     * 定义一个全局标记
     */
    public static Integer flag = 1;
}

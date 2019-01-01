package com.gaoyua.score.domain;

import java.util.Date;

/**
 * 功能描述:
 *
 * @author yaoyizhou
 * @date 2018/12/31 14:44
 * @desc
 */
public class Record {

    /**
     * 裁判编号
     */
    private String referee;

    /**
     * 1,代表蓝方；2代表红方
     */
    private Integer athlete;
    /**
     * 分数，1<=score<=4
     */
    private Integer score;

    /**
     * 犯规次数
     */
    private Integer foulNum;

    /**
     * 时间戳
     */
    private Date created;

    /**
     * 标记是否已经使用
     * 1：未匹配计算
     * 2：已使用
     */
    private Integer flag;


    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public Integer getAthlete() {
        return athlete;
    }

    public void setAthlete(Integer athlete) {
        this.athlete = athlete;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getFoulNum() {
        return foulNum;
    }

    public void setFoulNum(Integer foulNum) {
        this.foulNum = foulNum;
    }


}

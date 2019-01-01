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
     * 红方或者蓝方
     */
    private String athleteName;
    /**
     * 分数，1<=score<=4
     */
    private int score;

    /**
     * 这项是主控制页面打的分数
     */
    private int baseScore;

    /**
     * 犯规次数
     */
    private int foulNum;

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

    public int getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(int baseScore) {
        this.baseScore = baseScore;
    }

    public Record() {
    }

    public Record(Integer athlete, int score, int foulNum, Date created, Integer flag) {
        this.score = score;
        this.foulNum = foulNum;
        this.created = created;
        this.flag = flag;
        setAthlete(athlete);
    }

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

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
        if (athlete != null && athlete == 1) {
            setAthleteName("蓝方");
        } else if (athlete != null && athlete == 2) {
            setAthleteName("红方");
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getFoulNum() {
        return foulNum;
    }

    public void setFoulNum(int foulNum) {
        this.foulNum = foulNum;
    }
}

package com.gaoyua.score.domain;

/**
 * 功能描述:
 *
 * @author yaoyizhou
 * @date 2018/12/31 14:44
 * @desc
 */
public class Record {
    /**
     * 1,代表蓝方；2代表红方
     */
    private int flag;
    /**
     * 分数，1<=score<=4
     */
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}

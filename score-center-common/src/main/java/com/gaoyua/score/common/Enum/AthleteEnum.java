package com.gaoyua.score.common.Enum;

/**
 * 功能描述:
 *
 * @author yaoyizhou
 * @date 2018/12/31 18:51
 * @desc
 */
public enum AthleteEnum {

    ATHLETERED(1, "红方"),
    ATHLETEBLEUE(2, "蓝方");

    private Integer type;
    private String desc;

    private AthleteEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}

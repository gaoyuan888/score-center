package com.gaoyua.score.common.Enum;

/**
 * 功能描述:
 *
 * @author yaoyizhou
 * @date 2018/12/31 19:43
 * @desc
 */
public enum RefereeEnum {

    REREREE_ONE(1, "refereeOne"),
    REREREE_TWO(2, "refereeTwo"),
    REREREE_THREE(3, "refereeThree");

    private Integer type;
    private String desc;

    private RefereeEnum(Integer type, String desc) {
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

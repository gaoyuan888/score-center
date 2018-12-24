package com.gaoyua.score.common;

/**
 * Created by xudazhou on 2016/4/21.
 */
public class ResultJson<T> {
    private boolean flag;
    private T data;

    public ResultJson() {
    }

    public ResultJson(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

package com.yong.doit.app;

public enum EventMode {

    MEMORY("记忆模式", Cst.EVENT_MODE_MEMORY), PROGRESS("进度模式", Cst.EVENT_MODE_PROGRESS);

    int mode;
    String value;

    EventMode(String value, int mode) {
        this.mode = mode;
        this.value = value;
    }

    public int getMode() {
        return mode;
    }

    @Override
    public String toString() {
        return value;
    }
}

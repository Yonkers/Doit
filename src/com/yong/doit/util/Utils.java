package com.yong.doit.util;

import com.yong.doit.app.Cst;
import com.yong.doit.app.PeriodType;

public class Utils {

    public static PeriodType getPeriodType(int typeId) {
        if (typeId == Cst.DAY) {
            return PeriodType.DAY;
        } else if (typeId == Cst.MONTH) {
            return PeriodType.MONTH;
        } else if (typeId == Cst.WEEK) {
            return PeriodType.WEEK;
        }
        return null;
    }
}

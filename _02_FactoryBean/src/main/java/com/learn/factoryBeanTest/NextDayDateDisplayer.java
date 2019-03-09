package com.learn.factoryBeanTest;

import org.joda.time.DateTime;

/**
 * Created by gustaov on 2019/3/9.
 */
public class NextDayDateDisplayer {
    private DateTime dateOfNextDay;

    public DateTime getDateOfNextDay() {
        return dateOfNextDay;
    }

    public void setDateOfNextDay(DateTime dateOfNextDay) {
        this.dateOfNextDay = dateOfNextDay;
    }
}

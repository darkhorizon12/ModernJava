package org.basic.chapter12;

import java.time.DayOfWeek;
import java.time.temporal.*;

public class CustTemporalAdjusters {

    public static TemporalAdjuster getNextWorkingDay() {
        TemporalAdjuster nextWorkingDay =
                TemporalAdjusters.ofDateAdjuster(
                        temporal -> {
                            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                            int addToDay = dow == DayOfWeek.FRIDAY ? 3 : dow == DayOfWeek.SATURDAY ? 2 : 1;

                            return temporal.plus(addToDay, ChronoUnit.DAYS);
                        }

                );

        return nextWorkingDay;
    }
}

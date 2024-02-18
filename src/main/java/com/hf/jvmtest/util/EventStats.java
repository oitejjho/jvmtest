package com.hf.jvmtest.util;

import com.hf.jvmtest.dto.EventRequest;
import com.hf.jvmtest.dto.StatResponse;

import java.util.List;

public class EventStats {

    public static StatResponse getStats(List<EventRequest> events) {
        StatResponse statResponse = new StatResponse();
        Long globalCount = 0L;
        Double globalSumX = Double.valueOf(0.0);
        Double globalAverageX = Double.valueOf(0.0);
        Long globalSumY = Long.valueOf(0);
        Double globalAverageY = Double.valueOf(0.0);

        for (EventRequest event : events) {
            globalCount += event.getCount();
            globalSumX += event.getSumX();
            globalSumY += event.getSumY();
            globalAverageX += event.getAverageX();
            globalAverageY += event.getAverageY();
        }

        statResponse.setCount(globalCount);
        statResponse.setSumX(DoubleCalculation.round(globalSumX, 10));
        statResponse.setSumY(globalSumY);
        statResponse.setAverageX(DoubleCalculation.round(globalAverageX, 10));
        statResponse.setAverageY(DoubleCalculation.round(globalAverageY, 10));
        if(globalCount > 0) {
            statResponse.setAverageX(DoubleCalculation.round(DoubleCalculation.divide(globalSumX, globalCount), 10));
            statResponse.setAverageY(DoubleCalculation.round(DoubleCalculation.divide(globalSumY, globalCount), 10));
        }

        return statResponse;
    }
}

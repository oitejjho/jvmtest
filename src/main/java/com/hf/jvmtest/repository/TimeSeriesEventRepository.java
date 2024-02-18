package com.hf.jvmtest.repository;

import com.hf.jvmtest.dto.EventRequest;
import com.hf.jvmtest.util.DoubleCalculation;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Repository
public class TimeSeriesEventRepository implements EventRepository {
    private static final Long ACTIVE_RECORD_IN_SECONDS = 60L;
    private static final SortedMap<Long, EventRequest> data = Collections.synchronizedSortedMap(new TreeMap<>(Collections.reverseOrder()));;

    public boolean insert(EventRequest event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }
        if (this.checkValidity(event)) {
            if (data.containsKey(event.getTimestamp())) {
                EventRequest oldEvent = data.get(event.getTimestamp());
                event.setCount(oldEvent.getCount() + 1);
                event.setSumX(DoubleCalculation.round(oldEvent.getSumX() + event.getValueX(), 10));
                event.setSumY(oldEvent.getSumY() + event.getValueY());
            } else {
                event.setCount(1L);
                event.setSumX(DoubleCalculation.round(event.getValueX(), 10));
                event.setSumY(event.getValueY());
            }
            event.setAverageX(DoubleCalculation.round(DoubleCalculation.divide(event.getSumX(), event.getCount()), 10));
            event.setAverageY(DoubleCalculation.round(DoubleCalculation.divide(event.getSumY(), event.getCount()), 10));
            data.put(event.getTimestamp(), event);
        }
        return true;
    }

    public List<EventRequest> getDataNewerThan(long timestamp) {
        if (data.isEmpty()) {
            return Collections.emptyList();
        }
        return data.headMap(timestamp - 1).values().stream().toList();//+1 headMap doesn't include the value
    }

    @Override
    public EventRequest getLastRecord() {
        if (data.isEmpty()) {
            return new EventRequest(System.currentTimeMillis() / 1000, 0.0, 0L);
        }
        Long latestTimeStamp = data.firstKey();
        return data.get(latestTimeStamp);
    }

    private boolean checkValidity(EventRequest event) {
        if (!data.isEmpty()) {
            Long latestTimeStamp = data.firstKey();
            return event.getTimestamp() >= latestTimeStamp || event.getTimestamp() >= (latestTimeStamp - ACTIVE_RECORD_IN_SECONDS);
        } else {
            return true;
        }
    }


}

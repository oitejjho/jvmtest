package com.hf.jvmtest.repository;

import com.hf.jvmtest.dto.EventRequest;

import java.util.List;

public interface EventRepository {
    boolean insert(EventRequest event);
    List<EventRequest> getDataNewerThan(long timestamp);

    EventRequest getLastRecord();

//    void removeInactiveRecords();
}

package com.hf.jvmtest.service;


import com.hf.jvmtest.dto.EventRequest;
import com.hf.jvmtest.dto.StatResponse;
import com.hf.jvmtest.repository.EventRepository;
import com.hf.jvmtest.util.EventStats;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository timeSeriesEventsRepository;
    public EventService(EventRepository eventRepository){
        this.timeSeriesEventsRepository = eventRepository;

    }

    public void saveEvents(List<EventRequest> events) {
        List<EventRequest> eventRequests = events.stream().sorted(Comparator.comparingLong(EventRequest::getTimestamp).reversed()).toList();
        for (EventRequest event: eventRequests) {
            this.timeSeriesEventsRepository.insert(event);
        }
    }

    public StatResponse getStatistics() {
        long lastRecordTimeInSeconda = this.timeSeriesEventsRepository.getLastRecord().getTimestamp();
        List<EventRequest> data = timeSeriesEventsRepository.getDataNewerThan((lastRecordTimeInSeconda - 60));
        return EventStats.getStats(data);
    }
}

package com.hf.jvmtest.util;

import com.hf.jvmtest.dto.EventRequest;

import java.util.List;

public class EventRequestValidator {

    public static Long MIN_Y = 1073741823L;
    public static Long MAX_Y = 2147483647L;

    private static Boolean validate(EventRequest eventRequest) {
        return eventRequest.getValueY() >= MIN_Y && eventRequest.getValueY() <= MAX_Y;
    }

    public static void validate(List<EventRequest> eventRequests) {
        eventRequests.forEach(eventRequest -> {
            if(!validate(eventRequest))
                throw new IllegalArgumentException("Y value validation failed");
        });
    }

}

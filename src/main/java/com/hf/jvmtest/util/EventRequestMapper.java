package com.hf.jvmtest.util;

import com.hf.jvmtest.dto.EventRequest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EventRequestMapper {

    private static final Long SECOND_DIVIDER = 1000L;
    public static List<EventRequest> map(String payload) {
        String[] lines = payload.split("\n");
        return Arrays.stream(lines).parallel().map(line -> {
            String[] values = line.split(",");
            if (values.length < 3)
                throw new IllegalArgumentException("Attribute missing exception");
            return new EventRequest(Long.parseLong(values[0]) / SECOND_DIVIDER, Double.parseDouble(values[1]), Long.parseLong(values[2]));
        }).collect(Collectors.toList());
    }

}

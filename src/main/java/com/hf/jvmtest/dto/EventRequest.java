package com.hf.jvmtest.dto;

import lombok.Data;

@Data
public class EventRequest {
    private Long timestamp;
    private Double valueX;
    private Long valueY;

    //calculated values
    private Long count;
    private Double sumX;
    private Long sumY;

    private Double averageX;
    private Double averageY;

    public EventRequest(Long timestamp, Double valueX, Long valueY) {
        this.timestamp = timestamp;
        this.valueX = valueX;
        this.valueY = valueY;
    }

}

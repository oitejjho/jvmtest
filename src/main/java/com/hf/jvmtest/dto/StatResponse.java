package com.hf.jvmtest.dto;

import lombok.Data;

@Data
public class StatResponse {
    private Long count;
    private Double sumX;
    private Double averageX;
    private Long sumY;
    private Double averageY;
}

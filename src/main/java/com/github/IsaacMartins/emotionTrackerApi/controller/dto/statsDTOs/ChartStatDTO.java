package com.github.IsaacMartins.emotionTrackerApi.controller.dto.statsDTOs;

import com.github.IsaacMartins.emotionTrackerApi.service.ChartPoint;

import java.time.LocalDateTime;
import java.util.List;

public record ChartStatDTO(LocalDateTime requestDate, List<ChartPoint> points) {
}

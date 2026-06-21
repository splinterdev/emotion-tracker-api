package com.github.IsaacMartins.emotionTrackerApi.service;

import java.time.LocalDateTime;

public record ChartPoint(int xAxisDay, double yAxisAverage, LocalDateTime dateTime) {

}

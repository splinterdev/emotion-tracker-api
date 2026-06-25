package com.github.IsaacMartins.emotionTrackerApi.service;

import java.time.LocalDate;

public record ChartPoint(int xAxisDay, double yAxisAverage, LocalDate date) {

}

package com.github.IsaacMartins.emotionTrackerApi.controller.dto.recordDTOs;

import com.github.IsaacMartins.emotionTrackerApi.entities.emotion.Emotion;

import java.util.List;

public record EmotionListDTO(List<Emotion> emotionList) {
}

package com.github.IsaacMartins.emotionTrackerApi.controller.dto.recordDTOs;

import com.github.IsaacMartins.emotionTrackerApi.entities.emotion.Emotion;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record EmotionListDTO(@NotNull(message = "Required field!")
                             @NotEmpty(message = "Emotion List can't be empty!")
                             List<Emotion> emotionList) {
}

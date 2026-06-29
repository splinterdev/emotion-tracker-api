package com.github.IsaacMartins.emotionTrackerApi.controller.dto.recordDTOs;

import com.github.IsaacMartins.emotionTrackerApi.controller.dto.SituationDTOs.SituationDTO;
import com.github.IsaacMartins.emotionTrackerApi.entities.emotion.Mood;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record RecordRequestDTO(@NotNull(message = "Required field!")
                               Mood mood,
                               @NotNull(message = "Required field!")
                               @Valid
                               EmotionListDTO emotions,
                               @Valid
                               SituationDTO situation,
                               String description) {
}

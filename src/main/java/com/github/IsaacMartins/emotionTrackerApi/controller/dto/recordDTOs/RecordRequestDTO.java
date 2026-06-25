package com.github.IsaacMartins.emotionTrackerApi.controller.dto.recordDTOs;

import com.github.IsaacMartins.emotionTrackerApi.controller.dto.SituationDTOs.SituationDTO;
import com.github.IsaacMartins.emotionTrackerApi.entities.emotion.Mood;

public record RecordRequestDTO(Mood mood,
                               EmotionListDTO emotions,
                               SituationDTO situation,
                               String description) {
}

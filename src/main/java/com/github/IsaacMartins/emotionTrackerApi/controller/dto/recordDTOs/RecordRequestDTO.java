package com.github.IsaacMartins.emotionTrackerApi.controller.dto.recordDTOs;

import com.github.IsaacMartins.emotionTrackerApi.controller.dto.SituationDTOs.SituationDTO;
import com.github.IsaacMartins.emotionTrackerApi.entities.emotion.Mood;

import java.util.List;

public record RecordRequestDTO(Mood mood,
                               List<RecordEmotionDTO> emotions,
                               SituationDTO situation,
                               String description) {
}

package com.github.IsaacMartins.emotionTrackerApi.controller.dto.recordDTOs;

import com.github.IsaacMartins.emotionTrackerApi.controller.dto.SituationDTOs.SituationDTO;
import com.github.IsaacMartins.emotionTrackerApi.entities.emotion.Mood;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record RecordResponseDTO(UUID id,
                                Mood mood,
                                List<RecordEmotionDTO> emotions,
                                SituationDTO situation,
                                String description,
                                LocalDateTime createdAt,
                                LocalDateTime updatedAt) {
}
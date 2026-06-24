package com.github.IsaacMartins.emotionTrackerApi.controller.mappers;

import com.github.IsaacMartins.emotionTrackerApi.controller.dto.recordDTOs.RecordEmotionDTO;
import com.github.IsaacMartins.emotionTrackerApi.entities.record.RecordEmotion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecordEmotionMapper {

    List<RecordEmotion> toEntities(List<RecordEmotionDTO> emotionDTO);
    List<RecordEmotionDTO> toDTOs(List<RecordEmotion> emotion);
}

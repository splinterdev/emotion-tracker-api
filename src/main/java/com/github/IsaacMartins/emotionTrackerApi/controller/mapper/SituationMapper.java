package com.github.IsaacMartins.emotionTrackerApi.controller.mapper;

import com.github.IsaacMartins.emotionTrackerApi.controller.dto.SituationDTOs.SituationDTO;
import com.github.IsaacMartins.emotionTrackerApi.entities.record.Situation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SituationMapper {

    Situation toEntity(SituationDTO situationDTO);
    SituationDTO toDTO(Situation situation);
}

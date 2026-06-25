package com.github.IsaacMartins.emotionTrackerApi.controller.mappers;

import com.github.IsaacMartins.emotionTrackerApi.controller.dto.recordDTOs.RecordRequestDTO;
import com.github.IsaacMartins.emotionTrackerApi.controller.dto.recordDTOs.RecordResponseDTO;
import com.github.IsaacMartins.emotionTrackerApi.entities.record.Record;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class RecordMapper {

    @Autowired
    SituationMapper situationMapper;

    @Autowired
    RecordEmotionMapper emotionMapper;


    @Mapping(target = "emotionList", expression = "java( emotionMapper.toEntityList(recordDTO.emotions()) )")
    @Mapping(target = "situation", expression = "java( situationMapper.toEntity(recordDTO.situation()) )")
    public abstract Record toEntity(RecordRequestDTO recordDTO);

    @Mapping(target = "emotions", expression = "java( emotionMapper.toDTOList(record.getEmotionList()) )")
    @Mapping(target = "situation" , expression = "java( situationMapper.toDTO(record.getSituation()) )")
    public abstract RecordResponseDTO toResponseDTO(Record record);
}

package com.github.IsaacMartins.emotionTrackerApi.controller.mappers;

import com.github.IsaacMartins.emotionTrackerApi.controller.dto.recordDTOs.EmotionListDTO;
import com.github.IsaacMartins.emotionTrackerApi.entities.emotion.Emotion;
import com.github.IsaacMartins.emotionTrackerApi.entities.record.RecordEmotion;
import org.mapstruct.Mapper;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RecordEmotionMapper {

    default List<RecordEmotion> toEntityList(EmotionListDTO emotionRequest) {

        List<Emotion> emotionList = emotionRequest.emotionList();
        List<RecordEmotion> entityList = new ArrayList<>();

        for(Emotion e : emotionList) {

            RecordEmotion re = new RecordEmotion();
            re.setEmotion(e);

            entityList.add(re);
        }

        entityList.forEach(System.out::println);

        return entityList;
    }

    default EmotionListDTO toDTOList(List<RecordEmotion> emotion) {
        return new EmotionListDTO(emotion.stream().map(RecordEmotion::getEmotion).toList());
    }
}

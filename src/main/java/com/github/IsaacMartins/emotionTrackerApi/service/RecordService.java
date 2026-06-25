package com.github.IsaacMartins.emotionTrackerApi.service;

import com.github.IsaacMartins.emotionTrackerApi.entities.record.Record;
import com.github.IsaacMartins.emotionTrackerApi.entities.record.RecordEmotion;
import com.github.IsaacMartins.emotionTrackerApi.entities.record.Situation;
import com.github.IsaacMartins.emotionTrackerApi.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository repository;

    public Record create(Record record) {

        List<RecordEmotion> emotionList = record.getEmotionList();

        for(RecordEmotion e : emotionList) {

            e.setRecord(record);
        }

        if(record.getSituation() != null) {

            Situation situation = record.getSituation();
            situation.setRecord(record);
        }

        return repository.save(record);
    }

    public Optional<Record> getRecord(UUID id) {

        return repository.findCompleteById(id);
    }

    public Page<Record> getPagedRecords(int page, int pageSize) {

        Pageable pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));

        return repository.findAllComplete(pageRequest);
    }

    public Record update(Record record, Record mappedRequest) {

        if(record.getId() == null) {
            throw new IllegalArgumentException("Entidade não persistida não pode ser atualizada.");
        }

        List<RecordEmotion> mappedEmotionList = mappedRequest.getEmotionList();
        List<RecordEmotion> currentEmotionList = record.getEmotionList();

        Situation currentSituation = record.getSituation();
        Situation mappedSituation = mappedRequest.getSituation();

        record.setMood(mappedRequest.getMood());
        record.setDescription(mappedRequest.getDescription());
        updateEmotionList(record, mappedEmotionList, currentEmotionList);
        updateSituation(record, currentSituation, mappedSituation);

        return repository.save(record);
    }

    public void delete(Record record) {

        repository.deleteById(record.getId());
    }

    public List<ChartPoint> getMoodChartPoints(LocalDateTime requestDate) {

        List<ChartPoint> points = new ArrayList<>();

        LocalDateTime dayBeforeRequest = LocalDateTime.of(
                requestDate.getYear(),
                requestDate.getMonth(),
                requestDate.getDayOfMonth(),
                0, 0, 0).minusDays(6);

        List<Record> recordList = repository.findAllBetween(requestDate, dayBeforeRequest);

        if(!recordList.isEmpty()) {

            generateMoodPoints(requestDate, recordList, points);
        }

        return points;
    }

    private static void generateMoodPoints(LocalDateTime requestDate, List<Record> recordList, List<ChartPoint> points) {

        for(int i = 0; i < 7; i++) {

            LocalDate date = requestDate.minusDays(i).toLocalDate();
            int day = date.getDayOfMonth();
            double averageDayMood = 0;

            List<Record> specificDayRecords = recordList
                    .stream()
                    .filter(r -> r.getCreatedAt().getDayOfMonth() == day)
                    .toList();

            if(!specificDayRecords.isEmpty()) {

                averageDayMood = specificDayRecords
                        .stream()
                        .map(r -> r.getMood().getValue())
                        .reduce(0.0, Double::sum) / specificDayRecords.size();
            }

            points.add(new ChartPoint(day, averageDayMood, date));
        }
    }

    private static void updateSituation(Record record, Situation currentSituation, Situation mappedSituation) {

        if(currentSituation != null && mappedSituation != null && !currentSituation.equals(mappedSituation)) {

            currentSituation.setTitle(mappedSituation.getTitle());
            currentSituation.setThought(mappedSituation.getThought());
            currentSituation.setBehavior(mappedSituation.getBehavior());

            record.setUpdatedAt(LocalDateTime.now());

        } else if(currentSituation == null && mappedSituation != null) {

            mappedSituation.setRecord(record);
            record.setSituation(mappedSituation);

            record.setUpdatedAt(LocalDateTime.now());

        } else if(currentSituation != null && mappedSituation == null) {

            record.setSituation(null);

            record.setUpdatedAt(LocalDateTime.now());
        }
    }

    private static void updateEmotionList(Record record, List<RecordEmotion> mappedEmotionList, List<RecordEmotion> currentEmotionList) {

        if(!mappedEmotionList.equals(currentEmotionList)) {

            for(RecordEmotion mre : mappedEmotionList) {

                if(!currentEmotionList.contains(mre)) {

                    mre.setRecord(record);
                    currentEmotionList.add(mre);
                }
            }

            currentEmotionList.removeIf(re -> !mappedEmotionList.contains(re));

            record.setUpdatedAt(LocalDateTime.now());
        }
    }
}

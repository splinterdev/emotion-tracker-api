package com.github.IsaacMartins.emotionTrackerApi.service;

import com.github.IsaacMartins.emotionTrackerApi.entities.record.Record;
import com.github.IsaacMartins.emotionTrackerApi.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository repository;

    public Record create(Record record) {

        return repository.save(record);
    }

    public Optional<Record> get(UUID id) {

        return repository.findCompleteById(id);
    }

    public Page<Record> getAll(Integer page, Integer pageSize) {

        Pageable pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));

        return repository.findAllComplete(pageRequest);
    }

    public Record update(Record record) {

        if(record.getId() == null) {
            throw new IllegalArgumentException("Entidade não persistida não pode ser atualizada.");
        }

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

        Optional<List<Record>> possibleRecordList = repository.findAllBetween(requestDate, dayBeforeRequest);

        if(possibleRecordList.isPresent()) {

            List<Record> recordList = possibleRecordList.get();

            for(int i = 0; i < 7; i++) {

                LocalDateTime dateTime = requestDate.minusDays(i);
                int day = dateTime.getDayOfMonth();
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

                points.add(new ChartPoint(day, averageDayMood, dateTime));
            }

        }

        return points;
    }
}

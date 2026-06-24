package com.github.IsaacMartins.emotionTrackerApi.controller;

import com.github.IsaacMartins.emotionTrackerApi.controller.dto.recordDTOs.RecordRequestDTO;
import com.github.IsaacMartins.emotionTrackerApi.controller.dto.recordDTOs.RecordResponseDTO;
import com.github.IsaacMartins.emotionTrackerApi.controller.dto.statsDTOs.ChartStatDTO;
import com.github.IsaacMartins.emotionTrackerApi.controller.mappers.RecordMapper;
import com.github.IsaacMartins.emotionTrackerApi.entities.record.Record;
import com.github.IsaacMartins.emotionTrackerApi.service.ChartPoint;
import com.github.IsaacMartins.emotionTrackerApi.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class RecordController implements GenericController {

    private final RecordService service;
    private final RecordMapper mapper;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody RecordRequestDTO requestDTO) {

        Record record = mapper.toEntity(requestDTO);
        service.create(record);

        URI location = generateHeaderLocation(record.getId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<Page<RecordResponseDTO>> getAll(@RequestParam(value = "page", defaultValue = "0")
                                                          int page,
                                                          @RequestParam(value = "page-size", defaultValue = "10")
                                                          int pageSize) {

        Page<Record> recordPage = service.getPagedRecords(page, pageSize);

        Page<RecordResponseDTO> dtoPage = recordPage.map(mapper::toResponseDTO);

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("{id}")
    public ResponseEntity<RecordResponseDTO> getOne(@PathVariable("id") String id) {

        Optional<Record> possibleRecord = service.getRecord(UUID.fromString(id));

        if(possibleRecord.isPresent()) {

            RecordResponseDTO recordDTO = mapper.toResponseDTO(possibleRecord.get());

            return ResponseEntity.ok(recordDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody RecordRequestDTO requestDTO) {

        Optional<Record> possibleRecord = service.getRecord(UUID.fromString(id));

        if(possibleRecord.isPresent()) {

            Record record = possibleRecord.get();

            Record mappedRequest = mapper.toEntity(requestDTO);

            Record updated = service.update(record, mappedRequest);

            return ResponseEntity.ok(updated);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {

        Optional<Record> possibleRecord = service.getRecord(UUID.fromString(id));

        if(possibleRecord.isPresent()) {
            Record record = possibleRecord.get();
            service.delete(record);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/moodstats")
    public ResponseEntity<ChartStatDTO> getMoodStatChartPoints() {

        LocalDateTime requestDate = LocalDateTime.now();
        List<ChartPoint> moodChartPoints = service.getMoodChartPoints(requestDate);

        ChartStatDTO chartStat = new ChartStatDTO(requestDate, moodChartPoints);

        return ResponseEntity.ok(chartStat);
    }
}

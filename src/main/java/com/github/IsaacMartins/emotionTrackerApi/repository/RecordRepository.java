package com.github.IsaacMartins.emotionTrackerApi.repository;

import com.github.IsaacMartins.emotionTrackerApi.entities.record.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RecordRepository extends JpaRepository<Record, UUID> {

}

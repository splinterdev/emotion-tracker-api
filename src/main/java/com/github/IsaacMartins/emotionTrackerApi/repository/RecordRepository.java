package com.github.IsaacMartins.emotionTrackerApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RecordRepository extends JpaRepository<Record , UUID> {

}

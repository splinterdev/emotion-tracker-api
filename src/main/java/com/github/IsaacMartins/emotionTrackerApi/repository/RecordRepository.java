package com.github.IsaacMartins.emotionTrackerApi.repository;

import com.github.IsaacMartins.emotionTrackerApi.entities.record.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecordRepository extends JpaRepository<Record, UUID> {

    @Query("select r from Record r left join fetch r.emotionList e left join fetch r.situation s where r.id = :id")
    Optional<Record> findCompleteById(@Param("id") UUID id);

    @Query("select r from Record r left join fetch r.emotionList e left join fetch r.situation s")
    Page<Record> findAllComplete(Pageable pageRequest);

    @Query("select r from Record r where r.createdAt between :dayBeforeRequest and :requestDate order by r.createdAt desc")
    List<Record> findAllBetween(@Param("requestDate") LocalDateTime requestDate, @Param("dayBeforeRequest") LocalDateTime dayBeforeRequest);
}

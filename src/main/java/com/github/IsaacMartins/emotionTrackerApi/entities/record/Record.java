package com.github.IsaacMartins.emotionTrackerApi.entities.record;

import com.github.IsaacMartins.emotionTrackerApi.entities.emotion.Mood;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString(exclude = {"emotionList", "situation"})
@NoArgsConstructor

@Entity
@Table(name = "tb_record")
@EntityListeners(AuditingEntityListener.class)
public class Record {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    @Enumerated(EnumType.STRING)
    private Mood mood;

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RecordEmotion> emotionList;

    @OneToOne(mappedBy = "record", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Situation situation;

    @Column
    private String description;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}

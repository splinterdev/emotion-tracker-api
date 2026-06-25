package com.github.IsaacMartins.emotionTrackerApi.entities.record;

import com.github.IsaacMartins.emotionTrackerApi.entities.emotion.Emotion;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor

@Entity
@Table(name = "tb_record_emotion")
public class RecordEmotion {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id")
    private Record record;

    @Column
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Include
    private Emotion emotion;
}

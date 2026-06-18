package com.github.IsaacMartins.emotionTrackerApi.entities.record;

import com.github.IsaacMartins.emotionTrackerApi.entities.emotion.Emotion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
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

    @Enumerated(EnumType.STRING)
    @Column
    private Emotion emotion;
}

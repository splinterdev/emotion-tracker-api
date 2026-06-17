package com.github.IsaacMartins.emotionTrackerApi.entities.record;

import com.github.IsaacMartins.emotionTrackerApi.entities.emotion.Emotion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor

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
    private Emotion emotion;
}

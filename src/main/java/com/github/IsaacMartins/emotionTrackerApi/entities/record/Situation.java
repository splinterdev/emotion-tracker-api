package com.github.IsaacMartins.emotionTrackerApi.entities.record;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor

@Entity
@Table(name = "tb_situation")
public class Situation {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    @EqualsAndHashCode.Include
    private String title;

    @Column
    @EqualsAndHashCode.Include
    private String thought;

    @Column
    @EqualsAndHashCode.Include
    private String behavior;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id")
    private Record record;
}

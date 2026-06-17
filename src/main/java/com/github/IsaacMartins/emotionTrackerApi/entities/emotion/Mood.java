package com.github.IsaacMartins.emotionTrackerApi.entities.emotion;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public enum Mood {

    EXCELENTE(5),
    BEM(4),
    NORMAL(3),
    MAL(2),
    PESSIMO(1);

    private final double value;
}

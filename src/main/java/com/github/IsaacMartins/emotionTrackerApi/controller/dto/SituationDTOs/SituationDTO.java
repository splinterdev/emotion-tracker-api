package com.github.IsaacMartins.emotionTrackerApi.controller.dto.SituationDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SituationDTO(@NotBlank(message = "Required field!")
                           @Size(max = 150, message = "Invalid title size.")
                           String title,
                           @NotBlank(message = "Required field!")
                           @Size(max = 400, message = "Invalid text size.")
                           String thought,
                           @NotBlank(message = "Required field!")
                           @Size(max = 400, message = "Invalid text size.")
                           String behavior) {
}

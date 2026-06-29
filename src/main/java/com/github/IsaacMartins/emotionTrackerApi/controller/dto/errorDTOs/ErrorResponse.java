package com.github.IsaacMartins.emotionTrackerApi.controller.dto.errorDTOs;

import java.util.List;

public record ErrorResponse(int status, String message, List<InvalidField> erros) {
}

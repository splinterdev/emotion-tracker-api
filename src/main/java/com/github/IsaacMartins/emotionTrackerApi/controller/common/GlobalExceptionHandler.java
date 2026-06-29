package com.github.IsaacMartins.emotionTrackerApi.controller.common;

import com.github.IsaacMartins.emotionTrackerApi.controller.dto.errorDTOs.ErrorResponse;
import com.github.IsaacMartins.emotionTrackerApi.controller.dto.errorDTOs.InvalidField;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.ArrayList;
import java.util.List;
import static com.github.IsaacMartins.emotionTrackerApi.controller.common.MessageHandler.clearMessageConstructorVariables;
import static com.github.IsaacMartins.emotionTrackerApi.controller.common.MessageHandler.treatDeserializationLocalizedMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static List<InvalidField> invalidFieldList = new ArrayList<>();
    private static StringBuilder messageSb = new StringBuilder("Validation Error.");

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        clearMessageConstructorVariables(messageSb, invalidFieldList);

        List<FieldError> fieldErrorList = e.getFieldErrors();
        invalidFieldList = fieldErrorList.stream().map(fe -> new InvalidField(fe.getField(), fe.getDefaultMessage())).toList();

        return (new ErrorResponse(HttpStatus.UNPROCESSABLE_CONTENT.value(), messageSb.toString(), invalidFieldList));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleMessageNotReadableException(HttpMessageNotReadableException e) {

        clearMessageConstructorVariables(messageSb, invalidFieldList);

        String localizedMessage = e.getLocalizedMessage();

        if(localizedMessage.split(": ")[1].contains("Cannot deserialize")) {

            treatDeserializationLocalizedMessage(localizedMessage, invalidFieldList, messageSb);
        } else {

            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.unprocessableContent().body(new ErrorResponse(HttpStatus.UNPROCESSABLE_CONTENT.value(), messageSb.toString(), invalidFieldList));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUntreatedErrors(RuntimeException e) {

        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred. Please contact the administration.", List.of());
    }


}

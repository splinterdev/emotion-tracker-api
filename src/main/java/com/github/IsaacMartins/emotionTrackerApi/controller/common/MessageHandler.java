package com.github.IsaacMartins.emotionTrackerApi.controller.common;

import com.github.IsaacMartins.emotionTrackerApi.controller.dto.errorDTOs.InvalidField;

import java.util.List;

public class MessageHandler {

    private static final int INDEX_FROM_ADDED_MESSAGE = 17;

    protected static void clearMessageConstructorVariables(StringBuilder messageSb, List<InvalidField> invalidFieldList) {

        invalidFieldList.clear();
        messageSb.delete(INDEX_FROM_ADDED_MESSAGE, messageSb.toString().length());
    }

    protected static void treatDeserializationLocalizedMessage(String message, List<InvalidField> invalidFieldList, StringBuilder sb) {

        String error = message.split(" \\\"")[1].split("\\\": ")[0];

        String field = extractFieldFromDeserializationMessage(message);

        String onlyAccepted = message.split(": \\[")[1];

        sb.append(" ").append(field).append(" only accept: [").append(onlyAccepted);

        invalidFieldList.add(new InvalidField(field, error));
    }

    private static String extractFieldFromDeserializationMessage(String message) {

        String path = message.split("` ")[0].split(" `")[1];

        String[] splitedPath = path.split("\\.");

        String className = splitedPath[splitedPath.length - 1];

        return className.equals("Mood") ? "mood" : "emotionList";
    }
}

package org.mokka88.commons.validation;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class ValidationResult {
    private Status status = Status.OK;
    private Map<String, List<Message>> results = new HashMap<>();

    public List<Message> getFlatResults() {
        return results.entrySet().stream() //
                .map(Map.Entry::getValue) //
                .flatMap(Collection::stream) //
                .collect(Collectors.toList());
    }

    private void add(Status status, String componentName, String message) {
        if (this.status.ordinal() < status.ordinal()) {
            this.status = status;
        }

        results.putIfAbsent(componentName, new ArrayList<>());
        results.get(componentName).add(new Message(status, message));
    }

    public void addError(String componentName, String message) {
        add(Status.ERROR, componentName, message);
    }

    public void addWarning(String componentName, String message) {
        add(Status.WARNING, componentName, message);
    }

    public void addAll(ValidationResult validationResult) {
        validationResult.results.forEach((name, messages) -> addMessages(name, messages));
    }

    private void addMessages(String name, List<Message> messages) {
        messages.forEach(m -> add(m.status, name, m.text));
    }

    public List<String> getMessages() {
        return getFlatResults().stream().map(r -> r.getText()).collect(Collectors.toList());
    }

    public enum Status {
        OK,
        WARNING,
        ERROR
    }

    @Getter
    public class Message {
        private Status status;
        private String text;

        public Message(Status status, String text) {
            this.status = status;
            this.text = text;
        }
    }
}

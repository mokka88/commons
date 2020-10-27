package org.mokka88.commons.validation;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Data object containing all relevant information regarding the validation results
 *
 * @author mokka88
 */
@Getter
public class ValidationResult {

  private Status status = Status.OK;
  private Map<String, List<Message>> results = new HashMap<>();

  /**
   * Get results without component names (just status and error messages)
   *
   * @return
   */
  public List<Message> getMessages() {
    return results.entrySet().stream() //
        .map(Map.Entry::getValue) //
        .flatMap(Collection::stream) //
        .collect(Collectors.toList());
  }

  private void add(Status status, String componentName, String message) {
    if (this.status.ordinal() < status.ordinal()) {
      this.status = status;
    }

    results.computeIfAbsent(componentName, v -> new ArrayList<>());
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

  /**
   * Get only error message (no status or component names)
   *
   * @return
   */
  public List<String> getMessageTexts() {
    return getMessages().stream().map(r -> r.getText()).collect(Collectors.toList());
  }

  public Map<String, List<Message>> getEverything() {
    return results;
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

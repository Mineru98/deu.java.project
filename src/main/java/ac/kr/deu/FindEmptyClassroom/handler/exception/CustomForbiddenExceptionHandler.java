package ac.kr.deu.FindEmptyClassroom.handler.exception;

import java.util.Map;

public class CustomForbiddenExceptionHandler extends IllegalStateException {

  private String message;
  private String errorMessage;
  private Map<String, String> errorMap;

  public CustomForbiddenExceptionHandler(String message) {
    super(message);
  }

  public CustomForbiddenExceptionHandler(
    String message,
    Map<String, String> errorMap
  ) {
    super(message);
    this.errorMap = errorMap;
  }

  public Map<String, String> getErrorMap() {
    return errorMap;
  }

  public CustomForbiddenExceptionHandler(String message, String errorMessage) {
    super(message);
    this.errorMessage = errorMessage;
  }
}

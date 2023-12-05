package ac.kr.deu.FindEmptyClassroom.handler.exception;

import java.util.Map;

public class CustomIllegalStateExceptionHandler extends IllegalStateException {

  private String message;
  private Map<String, String> errorMap;
  private String errorMessage;

  public CustomIllegalStateExceptionHandler(String message, Throwable cause) {
    super(message, cause);
  }

  public CustomIllegalStateExceptionHandler(String message) {
    super(message);
  }

  public CustomIllegalStateExceptionHandler(
    String message,
    Map<String, String> errorMap
  ) {
    super(message);
    this.errorMap = errorMap;
  }

  public Map<String, String> getErrorMap() {
    return errorMap;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public CustomIllegalStateExceptionHandler(
    String message,
    String errorMessage
  ) {
    super(message);
    this.errorMessage = errorMessage;
  }
}

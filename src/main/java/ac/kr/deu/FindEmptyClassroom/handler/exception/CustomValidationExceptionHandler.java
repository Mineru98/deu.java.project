package ac.kr.deu.FindEmptyClassroom.handler.exception;

import java.util.Map;
import javax.validation.ValidationException;

public class CustomValidationExceptionHandler extends ValidationException {

  private String message;
  private Map<String, String> errorMap;

  public CustomValidationExceptionHandler(String message) {
    super(message);
  }

  public CustomValidationExceptionHandler(
    String message,
    Map<String, String> errorMap
  ) {
    super(message);
    this.errorMap = errorMap;
  }

  public Map<String, String> getErrorMap() {
    return errorMap;
  }
}

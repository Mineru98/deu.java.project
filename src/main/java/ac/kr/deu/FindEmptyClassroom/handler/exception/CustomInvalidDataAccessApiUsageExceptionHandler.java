package ac.kr.deu.FindEmptyClassroom.handler.exception;

import org.springframework.dao.InvalidDataAccessApiUsageException;

public class CustomInvalidDataAccessApiUsageExceptionHandler
  extends InvalidDataAccessApiUsageException {

  public CustomInvalidDataAccessApiUsageExceptionHandler(String msg) {
    super(msg);
  }
}

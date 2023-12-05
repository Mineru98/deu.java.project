package ac.kr.deu.FindEmptyClassroom.handler.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Order(ViewControllerException.ORDER)
@ControllerAdvice
public class ViewControllerException {

  public static final int ORDER = 0;

  /**
   * 개발자가 미처 잡지 못한에러에 대한 핸들러
   *
   * @param e       :error msg
   * @param request : HttpServletRequest
   * @return : HashMap<String, Object>
   */
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ClassCastException.class)
  public String caseExceptionsHandler(Exception e, HttpServletRequest request) {
    e.printStackTrace();
    return "auth/login";
  }
}

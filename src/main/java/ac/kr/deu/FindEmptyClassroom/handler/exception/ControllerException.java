package ac.kr.deu.FindEmptyClassroom.handler.exception;

import ac.kr.deu.FindEmptyClassroom.enums.ResponseState;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(ControllerException.ORDER)
@RestControllerAdvice
public class ControllerException {

  public static final int ORDER = 1; // 타입때문에 레퍼클래스로 정의하지 못했습니다.

  /**
   * 개발자가 미처 잡지 못한에러에 대한 핸들러
   *
   * @param e       :error msg
   * @param request : HttpServletRequest
   * @return : HashMap<String, Object>
   */
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(Exception.class)
  public ResponseDTO<?> allExceptionsHandler(
    Exception e,
    HttpServletRequest request
  ) {
    e.printStackTrace();
    return new ResponseDTO<>(
      ResponseState.ERROR,
      "알 수 없는 오류가 발생했습니다. 관리자에게 문의해 주세요."
    );
  }

  /**
   * @param e : error msg
   * @return :HashMap<String, Object>
   * @Valid 어노테이션에 의해서 유효성 검증 에러 핸들러
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(CustomValidationExceptionHandler.class)
  public ResponseDTO<?> validateException(CustomValidationExceptionHandler e) {
    return new ResponseDTO<>(ResponseState.ERROR, e.getMessage());
  }

  /**
   * 개발자가 의도한 런타임 오류(IllegalStateException) 핸들러
   *
   * @param e :error msg
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(CustomIllegalStateExceptionHandler.class)
  public ResponseDTO<?> CustomIllegalStateException(
    CustomIllegalStateExceptionHandler e
  ) {
    return new ResponseDTO<>(ResponseState.ERROR, e.getMessage());
  }

  /**
   * 개발자가 의도한 잘못된 접근에 대한 방어용 에러 핸들러
   *
   * @param e :error msg
   */
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler(CustomForbiddenExceptionHandler.class)
  public ResponseDTO<?> CustomForbiddenException(
    CustomForbiddenExceptionHandler e
  ) {
    return new ResponseDTO<>(ResponseState.ERROR, e.getMessage());
  }

  /**
   * db에 조회할때 제약조건에 맞지 않거나 데이터가 꼬여서 원하는 로우의 값이 나오지않았을때 발생하는 에러 핸들러
   *
   * @param e :error msg
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(CustomInvalidDataAccessApiUsageExceptionHandler.class)
  public ResponseDTO<?> CustomInvalidDataAccessApiUsageException(
    CustomInvalidDataAccessApiUsageExceptionHandler e
  ) {
    return new ResponseDTO<>(ResponseState.ERROR, e.getMessage());
  }
}

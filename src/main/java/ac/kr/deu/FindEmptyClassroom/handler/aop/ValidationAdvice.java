package ac.kr.deu.FindEmptyClassroom.handler.aop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Slf4j
@Component
@Aspect
public class ValidationAdvice {

  @Around(
    "execution(public * kr.ac.deu.computer_engineering.Absenteeism.Management.route..*(..))"
  )
  public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint)
    throws Throwable {
    log.info("[AOP]: Validation check AOP start");
    Object[] args = proceedingJoinPoint.getArgs();
    for (Object arg : args) {
      if (arg instanceof BindingResult) {
        BindingResult bindingResult = (BindingResult) arg;
        if (bindingResult.hasErrors()) {
          List<String> errorMsg = new ArrayList<>();
          Map<String, Object> errorMap = new HashMap<>();
          for (FieldError error : bindingResult.getFieldErrors()) {
            errorMsg.add(error.getDefaultMessage());
          }
          errorMap.put("message", errorMsg);
          return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
      }
    }
    return proceedingJoinPoint.proceed();
  }
}

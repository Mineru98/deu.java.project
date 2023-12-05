package ac.kr.deu.FindEmptyClassroom.router.auth;

import ac.kr.deu.FindEmptyClassroom.domain.User.dto.AuthDTO;
import ac.kr.deu.FindEmptyClassroom.domain.User.dto.LoginDTO;
import ac.kr.deu.FindEmptyClassroom.enums.ResponseState;
import ac.kr.deu.FindEmptyClassroom.handler.exception.ResponseDTO;
import ac.kr.deu.FindEmptyClassroom.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class AuthController {

  private final AuthService authService;

  @Tag(name = "인증")
  @Operation(summary = "사용자 로그인", description = "사용자 로그인")
  @PostMapping("/login")
  public ResponseEntity<?> login(
    HttpServletRequest request,
    @RequestBody(required = true) LoginDTO dto
  ) {
    AuthDTO auth = authService.login(
      dto.getUsername(),
      dto.getPassword(),
      dto.getUniversityId()
    );
    HttpSession session = request.getSession();
    session.setAttribute("userId", auth.getUserId());
    session.setAttribute("name", auth.getName());
    return new ResponseEntity<>(
      new ResponseDTO<>(ResponseState.OK, auth),
      HttpStatus.OK
    );
  }

  @Tag(name = "인증")
  @Operation(summary = "회원가입", description = "회원가입")
  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody(required = true) LoginDTO dto) {
    authService.signup(
      dto.getUsername(),
      dto.getPassword(),
      dto.getUniversityId()
    );
    return new ResponseEntity<>(
      new ResponseDTO<>(ResponseState.OK),
      HttpStatus.OK
    );
  }

  @Tag(name = "인증")
  @Operation(summary = "사용자 로그아웃", description = "사용자 로그아웃")
  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.invalidate();
    return new ResponseEntity<>(
      new ResponseDTO<>(ResponseState.OK),
      HttpStatus.OK
    );
  }
}

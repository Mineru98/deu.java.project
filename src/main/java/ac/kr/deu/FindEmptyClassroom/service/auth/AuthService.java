package ac.kr.deu.FindEmptyClassroom.service.auth;

import ac.kr.deu.FindEmptyClassroom.domain.University.University;
import ac.kr.deu.FindEmptyClassroom.domain.University.UniversityRepository;
import ac.kr.deu.FindEmptyClassroom.domain.User.User;
import ac.kr.deu.FindEmptyClassroom.domain.User.UserRepository;
import ac.kr.deu.FindEmptyClassroom.domain.User.dto.AuthDTO;
import ac.kr.deu.FindEmptyClassroom.enums.AuthorityRole;
import ac.kr.deu.FindEmptyClassroom.handler.exception.CustomIllegalStateExceptionHandler;
import ac.kr.deu.FindEmptyClassroom.utils.Encrypt;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final UniversityRepository universityRepository;

  @Transactional(readOnly = true)
  public AuthDTO login(String username, String password, Long universityId) {
    AuthDTO authDto = new AuthDTO();
    Optional<University> universityEntity = universityRepository.findById(
      universityId
    );
    if (universityEntity.isPresent()) {
      // 유저네임이 동일한게 있는지 확인
      Long checkUsername = userRepository.countByUsernameAndUniversity(
        username,
        universityEntity.get()
      );
      if (checkUsername > 0) {
        String encodingPassword = Encrypt.encode(password);
        Optional<User> checkUsernameAndPassword = userRepository.findByUsernameAndPasswordAndUniversity(
          username,
          encodingPassword,
          universityEntity.get()
        );
        if (checkUsernameAndPassword.isPresent()) {
          User user = checkUsernameAndPassword.get();
          authDto.setUserId(user.getUserId());
          authDto.setName(user.getUsername());
        } else {
          // 로그인 계정은 존재하지만, 비밀번호가 틀린 경우
          throw new CustomIllegalStateExceptionHandler(
            "계정과 비밀번호를 다시 확인해주세요."
          );
        }
      } else {
        // 로그인 계정이 존재하지 않는 경우
        throw new CustomIllegalStateExceptionHandler(
          "계정과 비밀번호를 다시 확인해주세요."
        );
      }
      return authDto;
    } else {
      // 해당 대학교가 존재하지 않는 경우
      throw new CustomIllegalStateExceptionHandler(
        "존재하지 않는 대학교 입니다."
      );
    }
  }

  @Transactional
  public void signup(String username, String password, Long universityId) {
    Optional<University> universityEntity = universityRepository.findById(
      universityId
    );
    if (universityEntity.isPresent()) {
      // 유저네임이 동일한게 있는지 확인
      Long checkUsername = userRepository.countByUsernameAndUniversity(
        username,
        universityEntity.get()
      );
      if (checkUsername == 0) {
        User userEntity = new User();
        userEntity.setUsername(username);
        String encodingPassword = Encrypt.encode(password);
        userEntity.setUserType(AuthorityRole.ROLE_USER.value());
        userEntity.setPassword(encodingPassword);
        userEntity.setUniversity(universityEntity.get());
        userRepository.save(userEntity);
      } else {
        throw new CustomIllegalStateExceptionHandler("이미 존재합니다.");
      }
    } else {
      // 해당 대학교가 존재하지 않는 경우
      throw new CustomIllegalStateExceptionHandler(
        "존재하지 않는 대학교 입니다."
      );
    }
  }
}

package ac.kr.deu.FindEmptyClassroom.domain.User.dto;

import lombok.Data;

@Data
public class LoginDTO {

  private String username;

  private String password;

  private Long universityId;
}

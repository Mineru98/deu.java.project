package ac.kr.deu.FindEmptyClassroom.enums;

public enum AuthorityRole {
  ROLE_ADMIN("관리자"),
  ROLE_MANGER("컨텐츠관리자"),
  ROLE_USER("사용자");

  final String value;

  AuthorityRole(String value) {
    this.value = value;
  }

  public String value() {
    return this.value;
  }
}

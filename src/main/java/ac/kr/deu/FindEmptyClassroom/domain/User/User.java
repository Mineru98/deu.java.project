package ac.kr.deu.FindEmptyClassroom.domain.User;

import ac.kr.deu.FindEmptyClassroom.domain.Claim.Claim;
import ac.kr.deu.FindEmptyClassroom.domain.University.University;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "User")
public class User {

  @Id
  @Column(name = "userId", updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Comment("계정명")
  @Column(unique = true, length = 64, nullable = false)
  private String username;

  @Comment("비밀번호(SHA512 암호화)")
  @Column(length = 512, unique = false)
  private String password;

  @Comment("사용자유형")
  @Column(length = 64, nullable = true)
  private String userType;

  @Comment("암호화 솔트")
  @Column(length = 64, nullable = true)
  private String salt;

  @Comment("학번(MD 암호화)")
  @Column(length = 16, nullable = false)
  private String classOf;

  @Comment("학번")
  @Column(length = 12, nullable = true)
  private String major;

  @Comment("본인인증여부")
  @Column(columnDefinition = "TINYINT(1)")
  @ColumnDefault("0")
  private Boolean isVerified;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @Comment("대학교 Id")
  @JsonBackReference
  @JoinColumn(name = "universityId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private University university;

  @JsonManagedReference
  @OneToMany(mappedBy = "claim", fetch = FetchType.LAZY)
  private List<Claim> claimList;

  @JsonManagedReference
  @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
  private List<ac.kr.deu.FindEmptyClassroom.domain.Comment.Comment> commentList;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (
      o == null || Hibernate.getClass(this) != Hibernate.getClass(o)
    ) return false;
    User that = (User) o;
    return userId != null && Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}

package ac.kr.deu.FindEmptyClassroom.domain.User;

import ac.kr.deu.FindEmptyClassroom.domain.Claim.Claim;
import ac.kr.deu.FindEmptyClassroom.domain.Reply.Reply;
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

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @PrePersist
  public void createDate() {
      this.createdAt = LocalDateTime.now();
      this.updatedAt = LocalDateTime.now();
  }

  @Comment("대학교 Id")
  @JsonBackReference
  @JoinColumn(name = "universityId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private University university;

  @JsonManagedReference
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<Claim> claimList;

  @JsonManagedReference
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<Reply> replyList;

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

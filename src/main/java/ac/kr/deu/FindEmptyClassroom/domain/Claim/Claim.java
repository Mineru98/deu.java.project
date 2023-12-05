package ac.kr.deu.FindEmptyClassroom.domain.Claim;

import ac.kr.deu.FindEmptyClassroom.domain.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Claim")
public class Claim {

  @Id
  @Column(name = "claimId", updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long claimId;

  @Comment("신고 내용")
  @Lob
  private String content;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

    @PrePersist
    public void createDate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
  @Comment("사용자 Id")
  @JsonBackReference
  @JoinColumn(name = "userId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private User user;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (
      o == null || Hibernate.getClass(this) != Hibernate.getClass(o)
    ) return false;
    Claim that = (Claim) o;
    return claimId != null && Objects.equals(claimId, that.claimId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}

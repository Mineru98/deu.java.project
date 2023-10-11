package ac.kr.deu.FindEmptyClassroom.domain.LikeComment;

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
@Table(name = "LikeComment")
public class LikeComment {

  @Id
  @Column(name = "likeId", updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long likeId;

  @Comment("사용자 Id")
  @JsonBackReference
  @JoinColumn(name = "userId")
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @Comment("댓글 Id")
  @JsonBackReference
  @JoinColumn(name = "commentId")
  @ManyToOne(fetch = FetchType.LAZY)
  private ac.kr.deu.FindEmptyClassroom.domain.Comment.Comment comment;

  public LikeComment(
    User user,
    ac.kr.deu.FindEmptyClassroom.domain.Comment.Comment comment
  ) {
    this.user = user;
    this.comment = comment;
  }

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (
      o == null || Hibernate.getClass(this) != Hibernate.getClass(o)
    ) return false;
    LikeComment that = (LikeComment) o;
    return likeId != null && Objects.equals(likeId, that.likeId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}

package ac.kr.deu.FindEmptyClassroom.domain.LikeReply;

import ac.kr.deu.FindEmptyClassroom.domain.Reply.Reply;
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
@Table(name = "LikeReply")
public class LikeReply {

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
  @JoinColumn(name = "replyId")
  @ManyToOne(fetch = FetchType.LAZY)
  private Reply reply;

  public LikeReply(
    User user,
    Reply reply
  ) {
    this.user = user;
    this.reply = reply;
  }

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @PrePersist
  public void createDate() {
      this.createdAt = LocalDateTime.now();
      this.updatedAt = LocalDateTime.now();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (
      o == null || Hibernate.getClass(this) != Hibernate.getClass(o)
    ) return false;
    LikeReply that = (LikeReply) o;
    return likeId != null && Objects.equals(likeId, that.likeId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}

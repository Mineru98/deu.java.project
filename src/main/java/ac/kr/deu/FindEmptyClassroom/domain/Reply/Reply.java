package ac.kr.deu.FindEmptyClassroom.domain.Reply;

import ac.kr.deu.FindEmptyClassroom.domain.Board.Board;
import ac.kr.deu.FindEmptyClassroom.domain.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
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
@Table(name = "Reply")
public class Reply {

  @Id
  @Column(name = "replyId", updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long replyId;

  @Comment("댓글 내용")
  @Column(length = 512, nullable = false)
  private String content;

  @Comment("계층")
  @ColumnDefault("1")
  @Column(nullable = false)
  private Integer layer;

  @Comment("순서")
  @ColumnDefault("1")
  @Column(nullable = false)
  private Integer rOrder;

  @Comment("댓글 그룹")
  @ColumnDefault("1")
  @Column(nullable = false)
  private Integer groupNum;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @Comment("작성자 Id")
  @JsonBackReference
  @JoinColumn(name = "userId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private User user;

  @Comment("게시판 Id")
  @JsonBackReference
  @JoinColumn(name = "boardId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private Board board;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (
      o == null || Hibernate.getClass(this) != Hibernate.getClass(o)
    ) return false;
    Reply that = (Reply) o;
    return replyId != null && Objects.equals(replyId, that.replyId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}

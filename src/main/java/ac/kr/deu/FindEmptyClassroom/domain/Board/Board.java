package ac.kr.deu.FindEmptyClassroom.domain.Board;

import ac.kr.deu.FindEmptyClassroom.domain.Course.Course;
import ac.kr.deu.FindEmptyClassroom.domain.User.User;
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
@Table(name = "Board")
public class Board {

  @Id
  @Column(name = "boardId", updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boardId;

  @Comment("게시글 제목")
  @Column(length = 128, nullable = false)
  private String title;

  @Comment("게시글 내용")
  @Lob
  private String content;

  @Comment("게시글 그룹")
  @Column(length = 128, nullable = false)
  private String group;

  @Comment("조회수")
  @ColumnDefault("0")
  @Column(nullable = true)
  private Integer viewCount;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @JsonManagedReference
  @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
  private List<ac.kr.deu.FindEmptyClassroom.domain.Comment.Comment> commentList;

  @Comment("수업 Id")
  @OneToOne(mappedBy = "board")
  private Course course;

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
    Board that = (Board) o;
    return boardId != null && Objects.equals(boardId, that.boardId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}

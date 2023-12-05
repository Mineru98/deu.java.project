package ac.kr.deu.FindEmptyClassroom.domain.Board;

import ac.kr.deu.FindEmptyClassroom.domain.Course.Course;
import ac.kr.deu.FindEmptyClassroom.domain.Reply.Reply;
import ac.kr.deu.FindEmptyClassroom.domain.Room.Room;
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

  @Comment("조회수")
  @ColumnDefault("0")
  @Column(nullable = true)
  private Integer viewCount;

  @Comment("반영년도")
  @ColumnDefault("2023")
  @Column(nullable = false)
  private Integer applyYear;

  @Comment("반영학기")
  @ColumnDefault("1")
  @Column(nullable = false)
  private Integer applySemester;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @PrePersist
  public void createDate() {
      this.createdAt = LocalDateTime.now();
      this.updatedAt = LocalDateTime.now();
  }

  @JsonManagedReference
  @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
  private List<Reply> replyList;

  @Comment("강의실 Id")
  @JoinColumn(name = "roomId")
  @OneToOne
  private Room room;

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

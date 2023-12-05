package ac.kr.deu.FindEmptyClassroom.domain.BoardLog;

import ac.kr.deu.FindEmptyClassroom.domain.Board.Board;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BoardLog")
public class BoardLog {

    @Id
    @Column(name = "boardLogId", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardLogId;

    @Comment("ip")
    @Column(length = 15, updatable = false)
    private String ip;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void createDate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

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
        BoardLog that = (BoardLog) o;
        return boardLogId != null && Objects.equals(boardLogId, that.boardLogId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

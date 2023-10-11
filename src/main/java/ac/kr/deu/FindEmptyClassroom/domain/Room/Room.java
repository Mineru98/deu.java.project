package ac.kr.deu.FindEmptyClassroom.domain.Room;

import ac.kr.deu.FindEmptyClassroom.domain.Building.Building;
import ac.kr.deu.FindEmptyClassroom.domain.Course.Course;
import ac.kr.deu.FindEmptyClassroom.domain.University.University;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDateTime;
import java.util.List;
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
@Table(name = "Room")
public class Room {

  @Id
  @Column(name = "roomId", updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long roomId;

  @Comment("강의실호실")
  @Column(length = 64, nullable = true)
  private String roomNumber;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @Comment("대학교 Id")
  @JsonBackReference
  @JoinColumn(name = "universityId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private University university;

  @Comment("건물 Id")
  @JsonBackReference
  @JoinColumn(name = "buildingId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private Building building;

  @JsonManagedReference
  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
  private List<Course> courseList;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (
      o == null || Hibernate.getClass(this) != Hibernate.getClass(o)
    ) return false;
    Room that = (Room) o;
    return roomId != null && Objects.equals(roomId, that.roomId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}

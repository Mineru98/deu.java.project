package ac.kr.deu.FindEmptyClassroom.domain.Building;

import ac.kr.deu.FindEmptyClassroom.domain.Course.Course;
import ac.kr.deu.FindEmptyClassroom.domain.Room.Room;
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
@Table(name = "Building")
public class Building {

  @Id
  @Column(name = "buildingId", updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long buildingId;

  @Comment("건물 번호")
  @Column(length = 12, nullable = true)
  private String buildingNumber;

  @Comment("건물명")
  @Column(length = 128, nullable = true)
  private String buildingName;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @Comment("대학교 Id")
  @JsonBackReference
  @JoinColumn(name = "universityId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private University university;

  @JsonManagedReference
  @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
  private List<Room> roomList;

  @JsonManagedReference
  @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
  private List<Course> courseList;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (
      o == null || Hibernate.getClass(this) != Hibernate.getClass(o)
    ) return false;
    Building that = (Building) o;
    return buildingId != null && Objects.equals(buildingId, that.buildingId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}

package ac.kr.deu.FindEmptyClassroom.domain.University;

import ac.kr.deu.FindEmptyClassroom.domain.Building.Building;
import ac.kr.deu.FindEmptyClassroom.domain.Course.Course;
import ac.kr.deu.FindEmptyClassroom.domain.Room.Room;
import ac.kr.deu.FindEmptyClassroom.domain.User.User;
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
@Table(name = "University")
public class University {

  @Id
  @Column(name = "universityId", updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long universityId;

  @Comment("학교 이름")
  @Column(length = 64, nullable = false)
  private String name;

  @Comment("학교 지역")
  @Column(length = 128, nullable = true)
  private String location;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @JsonManagedReference
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<User> userList;

  @JsonManagedReference
  @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
  private List<Building> buildingList;

  @JsonManagedReference
  @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
  private List<Room> roomList;

  @JsonManagedReference
  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
  private List<Course> courseList;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (
      o == null || Hibernate.getClass(this) != Hibernate.getClass(o)
    ) return false;
    University that = (University) o;
    return (
      universityId != null && Objects.equals(universityId, that.universityId)
    );
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}

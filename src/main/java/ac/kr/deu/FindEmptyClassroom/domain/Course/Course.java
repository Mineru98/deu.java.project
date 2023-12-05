package ac.kr.deu.FindEmptyClassroom.domain.Course;

import ac.kr.deu.FindEmptyClassroom.domain.Board.Board;
import ac.kr.deu.FindEmptyClassroom.domain.Building.Building;
import ac.kr.deu.FindEmptyClassroom.domain.Room.Room;
import ac.kr.deu.FindEmptyClassroom.domain.University.University;
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
@Table(name = "Course")
public class Course {

  @Id
  @Column(name = "courseId", updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long courseId;

  @Comment("강의명")
  @Column(length = 64, nullable = true)
  private String courseName;

  @Comment("교수명")
  @Column(length = 64, nullable = true)
  private String professor;

  @Comment("개설학과")
  @Column(length = 64, nullable = true)
  private String department;

  @Comment("분반")
  @Column(length = 64, nullable = true)
  private String divideNumber;

  @Comment("교과목번호")
  @Column(length = 64, nullable = true)
  private String courseNumber;

  @Comment("요일")
  @ColumnDefault("1")
  @Column(nullable = true)
  private Integer courseDayOf;

  @Comment("시간대")
  @ColumnDefault("1")
  @Column(nullable = true)
  private Integer courseTime;

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

  @Comment("강의실 Id")
  @JsonBackReference
  @JoinColumn(name = "roomId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private Room room;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (
      o == null || Hibernate.getClass(this) != Hibernate.getClass(o)
    ) return false;
    Course that = (Course) o;
    return courseId != null && Objects.equals(courseId, that.courseId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}

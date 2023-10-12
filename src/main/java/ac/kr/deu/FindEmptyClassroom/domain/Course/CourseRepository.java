package ac.kr.deu.FindEmptyClassroom.domain.Course;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
  <T> Optional<T> findByCourseId(Long courseId, Class<T> type);

  void deleteByCourseId(Long courseId);
}

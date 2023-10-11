package ac.kr.deu.FindEmptyClassroom.domain.Course;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
  <T> Optional<T> findById(Long id, Class<T> type);

  void deleteById(Long id);
}

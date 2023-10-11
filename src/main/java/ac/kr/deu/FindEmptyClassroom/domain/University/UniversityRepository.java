package ac.kr.deu.FindEmptyClassroom.domain.University;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {
  <T> Optional<T> findById(Long id, Class<T> type);

  void deleteById(Long id);
}

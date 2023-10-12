package ac.kr.deu.FindEmptyClassroom.domain.University;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {
  <T> Optional<T> findByUniversityId(Long universityId, Class<T> type);

  void deleteByUniversityId(Long universityId);
}

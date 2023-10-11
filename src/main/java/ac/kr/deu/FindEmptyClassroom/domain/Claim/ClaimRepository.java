package ac.kr.deu.FindEmptyClassroom.domain.Claim;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
  <T> Optional<T> findById(Long id, Class<T> type);

  void deleteById(Long id);
}

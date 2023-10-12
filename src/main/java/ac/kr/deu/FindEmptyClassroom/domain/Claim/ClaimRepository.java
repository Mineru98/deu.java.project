package ac.kr.deu.FindEmptyClassroom.domain.Claim;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
  <T> Optional<T> findByClaimId(Long claimId, Class<T> type);

  void deleteByClaimId(Long claimId);
}

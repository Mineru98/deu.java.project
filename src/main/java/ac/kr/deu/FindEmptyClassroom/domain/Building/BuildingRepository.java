package ac.kr.deu.FindEmptyClassroom.domain.Building;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {
  <T> Optional<T> findByBuildingId(Long buildingId, Class<T> type);

  void deleteByBuildingId(Long buildingId);
}

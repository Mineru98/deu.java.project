package ac.kr.deu.FindEmptyClassroom.domain.Building;

import java.util.List;
import java.util.Optional;

import ac.kr.deu.FindEmptyClassroom.domain.Room.Room;
import ac.kr.deu.FindEmptyClassroom.domain.University.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {
  <T> Optional<T> findByBuildingId(Long buildingId, Class<T> type);

  List<Building> findAllByUniversityOrderByBuildingName(University university);

  void deleteByBuildingId(Long buildingId);
}

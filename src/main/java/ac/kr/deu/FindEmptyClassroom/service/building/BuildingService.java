package ac.kr.deu.FindEmptyClassroom.service.building;

import ac.kr.deu.FindEmptyClassroom.domain.Building.Building;
import ac.kr.deu.FindEmptyClassroom.domain.Building.BuildingRepository;
import ac.kr.deu.FindEmptyClassroom.domain.University.University;
import ac.kr.deu.FindEmptyClassroom.domain.University.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuildingService {
    private final BuildingRepository buildingRepository;
    private final UniversityRepository universityRepository;

    @Transactional
    public List<Building> findAllByUniversityId(Long universityId) {
        Optional<University> entity = universityRepository.findByUniversityId(universityId, University.class);
        if (entity.isPresent()) {
            return buildingRepository.findAllByUniversityOrderByBuildingName(entity.get());
        } else {
            return new ArrayList<>();
        }
    }
}

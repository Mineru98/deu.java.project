package ac.kr.deu.FindEmptyClassroom.service.course;

import ac.kr.deu.FindEmptyClassroom.domain.Course.CourseRepository;
import ac.kr.deu.FindEmptyClassroom.domain.Course.dto.SearchMapping;
import ac.kr.deu.FindEmptyClassroom.domain.Req.RequestDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseService {

  private final CourseRepository courseRepository;

  @Transactional
  public List<SearchMapping> getAllByCondition(RequestDTO dto) {
    if (
      dto.getUniversityId() != null &&
      dto.getCourseDayOf() != null &&
      dto.getCourseTime() != null
    ) {
      List<SearchMapping> rooms = new ArrayList<>();
      List<SearchMapping> roomsCompare = new ArrayList<>();

      if (dto.getBuildingId() == null && dto.getRoomName() == null) {
        rooms = courseRepository.findAll(SearchMapping.class);
      } else if (dto.getBuildingId() != null && dto.getRoomName() == null) {
        rooms = courseRepository.findAllByBuildingId(dto.getBuildingId(), SearchMapping.class);
      } else if (dto.getBuildingId() == null && dto.getRoomName() != null) {
        rooms = courseRepository.findAllByRoomName(dto.getRoomName(), SearchMapping.class);
      } else {
        rooms = courseRepository.findAllByBuildingIdAndRoomName(dto.getBuildingId(), dto.getRoomName(), SearchMapping.class);
      }

      if (dto.getBuildingId() == null && dto.getRoomName() == null) {
        roomsCompare =
          courseRepository.findByUniversityIdAndCourseTimeAndCourseDayOf(
            dto.getUniversityId(),
            dto.getCourseTime(),
            dto.getCourseDayOf(),
            SearchMapping.class
          );
      } else if (dto.getBuildingId() != null && dto.getRoomName() == null) {
        roomsCompare =
          courseRepository.findByUniversityIdAndCourseTimeAndCourseDayOfAndBuildingId(
            dto.getUniversityId(),
            dto.getCourseTime(),
            dto.getCourseDayOf(),
            dto.getBuildingId(),
            SearchMapping.class
          );
      } else if (dto.getBuildingId() == null && dto.getRoomName() != null) {
        roomsCompare =
          courseRepository.findByUniversityIdAndCourseTimeAndCourseDayOfAndRoomName(
            dto.getUniversityId(),
            dto.getCourseTime(),
            dto.getCourseDayOf(),
            dto.getRoomName(),
            SearchMapping.class
          );
      } else {
        roomsCompare =
          courseRepository.findByUniversityIdAndCourseTimeAndCourseDayOfAndRoomNameAndBuildingId(
            dto.getUniversityId(),
            dto.getCourseTime(),
            dto.getCourseDayOf(),
            dto.getRoomName(),
            dto.getBuildingId(),
            SearchMapping.class
          );
      }

      Set<Long> roomIdsInA = roomsCompare
        .stream()
        .map(SearchMapping::getRoomId)
        .collect(Collectors.toSet());
      rooms.removeIf(item -> roomIdsInA.contains(item.getRoomId()));
      return rooms;
    } else {
      return new ArrayList<>();
    }
  }
}

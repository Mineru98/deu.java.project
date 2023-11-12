package ac.kr.deu.FindEmptyClassroom.service.course;

import ac.kr.deu.FindEmptyClassroom.domain.Course.CourseRepository;
import ac.kr.deu.FindEmptyClassroom.domain.Course.dto.SearchMapping;
import ac.kr.deu.FindEmptyClassroom.domain.Req.RequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    @Transactional
    public List<SearchMapping> getAllByCondition(RequestDTO dto) {
        if (dto.getUniversityId() != null && dto.getCourseDayOf() != null && dto.getCourseTime() != null) {
            if (dto.getBuildingId() == null && dto.getRoomName() == null) {
                return courseRepository.findByUniversityIdAndCourseTimeAndCourseDayOf(dto.getUniversityId(), dto.getCourseTime(), dto.getCourseDayOf(), SearchMapping.class);
            } else if (dto.getBuildingId() != null && dto.getRoomName() == null) {
                return courseRepository.findByUniversityIdAndCourseTimeAndCourseDayOfAndBuildingId(dto.getUniversityId(), dto.getCourseTime(), dto.getCourseDayOf(), dto.getBuildingId(), SearchMapping.class);
            } else if (dto.getBuildingId() == null && dto.getRoomName() != null) {
                return courseRepository.findByUniversityIdAndCourseTimeAndCourseDayOfAndRoomName(dto.getUniversityId(), dto.getCourseTime(), dto.getCourseDayOf(), dto.getRoomName(), SearchMapping.class);
            } else {
                return courseRepository.findByUniversityIdAndCourseTimeAndCourseDayOfAndRoomNameAndBuildingId(dto.getUniversityId(), dto.getCourseTime(), dto.getCourseDayOf(), dto.getRoomName(), dto.getBuildingId(), SearchMapping.class);
            }
        } else {
            return new ArrayList<>();
        }
    }
}

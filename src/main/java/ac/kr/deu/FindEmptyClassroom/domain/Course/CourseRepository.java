package ac.kr.deu.FindEmptyClassroom.domain.Course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long> {
  <T> Optional<T> findByCourseId(Long courseId, Class<T> type);

  @Query(value =
          "SELECT DISTINCT " +
                  "R.roomId, " +
                  "B.buildingName, " +
                  "B.buildingNumber, " +
                  "R.roomNumber, " +
                  "C.courseTime, " +
                  "C.courseDayOf, " +
                  "CASE " +
                  "        WHEN C.courseDayOf = 1 THEN '월요일' " +
                  "        WHEN C.courseDayOf = 2 THEN '화요일' " +
                  "        WHEN C.courseDayOf = 3 THEN '수요일' " +
                  "        WHEN C.courseDayOf = 4 THEN '목요일' " +
                  "        WHEN C.courseDayOf = 5 THEN '금요일' " +
                  "        WHEN C.courseDayOf = 6 THEN '토요일' " +
                  "        WHEN C.courseDayOf = 0 THEN '일요일' " +
                  "        ELSE '월요일' " +
                  "    END AS formattedCourseDayOf, " +
                  "CASE " +
                  "        WHEN C.courseTime = 1 THEN '1교시 (09:00~10:00)' " +
                  "        WHEN C.courseTime = 2 THEN '2교시 (10:00~11:00)' " +
                  "        WHEN C.courseTime = 3 THEN '3교시 (11:00~12:00)' " +
                  "        WHEN C.courseTime = 4 THEN '4교시 (12:00~13:00)' " +
                  "        WHEN C.courseTime = 5 THEN '5교시 (13:00~14:00)' " +
                  "        WHEN C.courseTime = 6 THEN '6교시 (14:00~15:00)' " +
                  "        WHEN C.courseTime = 7 THEN '7교시 (15:00~16:00)' " +
                  "        WHEN C.courseTime = 8 THEN '8교시 (16:00~17:00)' " +
                  "        WHEN C.courseTime = 9 THEN '9교시 (17:00~18:00)' " +
                  "        ELSE '1교시 (09:00~10:00)' " +
                  "    END AS formattedCourseTime " +
                  "FROM " +
                  "Course AS C " +
                  "INNER JOIN University AS U ON U.universityId = C.universityId " +
                  "INNER JOIN Building AS B ON B.buildingId = C.buildingId " +
                  "INNER JOIN Room AS R ON R.roomId = C.roomId " +
                  "WHERE C.universityId = :universityId AND " +
                  "C.courseTime IN :courseTime AND " +
                  "C.courseDayOf IN :courseDayOf " +
                  "ORDER BY C.courseDayOf, C.courseTime, B.buildingName, B.buildingNumber;",
          nativeQuery = true)
  <T> List<T> findByUniversityIdAndCourseTimeAndCourseDayOf(@Param("universityId") Long universityId, @Param("courseTime") List<Long> courseTime, @Param("courseDayOf") List<Long> courseDayOf, Class<T> type);

  @Query(value =
          "SELECT DISTINCT " +
                  "R.roomId, " +
                  "B.buildingName, " +
                  "B.buildingNumber, " +
                  "R.roomNumber, " +
                  "C.courseTime, " +
                  "C.courseDayOf, " +
                  "CASE " +
                  "        WHEN C.courseDayOf = 1 THEN '월요일' " +
                  "        WHEN C.courseDayOf = 2 THEN '화요일' " +
                  "        WHEN C.courseDayOf = 3 THEN '수요일' " +
                  "        WHEN C.courseDayOf = 4 THEN '목요일' " +
                  "        WHEN C.courseDayOf = 5 THEN '금요일' " +
                  "        WHEN C.courseDayOf = 6 THEN '토요일' " +
                  "        WHEN C.courseDayOf = 0 THEN '일요일' " +
                  "        ELSE '월요일' " +
                  "    END AS formattedCourseDayOf, " +
                  "CASE " +
                  "        WHEN C.courseTime = 1 THEN '1교시 (09:00~10:00)' " +
                  "        WHEN C.courseTime = 2 THEN '2교시 (10:00~11:00)' " +
                  "        WHEN C.courseTime = 3 THEN '3교시 (11:00~12:00)' " +
                  "        WHEN C.courseTime = 4 THEN '4교시 (12:00~13:00)' " +
                  "        WHEN C.courseTime = 5 THEN '5교시 (13:00~14:00)' " +
                  "        WHEN C.courseTime = 6 THEN '6교시 (14:00~15:00)' " +
                  "        WHEN C.courseTime = 7 THEN '7교시 (15:00~16:00)' " +
                  "        WHEN C.courseTime = 8 THEN '8교시 (16:00~17:00)' " +
                  "        WHEN C.courseTime = 9 THEN '9교시 (17:00~18:00)' " +
                  "        ELSE '1교시 (09:00~10:00)' " +
                  "    END AS formattedCourseTime " +
                  "FROM " +
                  "Course AS C " +
                  "INNER JOIN University AS U ON U.universityId = C.universityId " +
                  "INNER JOIN Building AS B ON B.buildingId = C.buildingId " +
                  "INNER JOIN Room AS R ON R.roomId = C.roomId " +
                  "WHERE C.universityId = :universityId AND " +
                  "C.courseTime IN :courseTime AND " +
                  "C.courseDayOf IN :courseDayOf AND " +
                  "R.roomNumber LIKE %:roomName% " +
                  "ORDER BY C.courseDayOf, C.courseTime, B.buildingName, B.buildingNumber;",
          nativeQuery = true)
  <T> List<T> findByUniversityIdAndCourseTimeAndCourseDayOfAndRoomName(@Param("universityId") Long universityId, @Param("courseTime") List<Long> courseTime, @Param("courseDayOf") List<Long> courseDayOf, @Param("roomName") String roomName, Class<T> type);

  @Query(value =
          "SELECT DISTINCT " +
                  "R.roomId, " +
                  "B.buildingName, " +
                  "B.buildingNumber, " +
                  "R.roomNumber, " +
                  "C.courseTime, " +
                  "C.courseDayOf, " +
                  "CASE " +
                  "        WHEN C.courseDayOf = 1 THEN '월요일' " +
                  "        WHEN C.courseDayOf = 2 THEN '화요일' " +
                  "        WHEN C.courseDayOf = 3 THEN '수요일' " +
                  "        WHEN C.courseDayOf = 4 THEN '목요일' " +
                  "        WHEN C.courseDayOf = 5 THEN '금요일' " +
                  "        WHEN C.courseDayOf = 6 THEN '토요일' " +
                  "        WHEN C.courseDayOf = 0 THEN '일요일' " +
                  "        ELSE '월요일' " +
                  "    END AS formattedCourseDayOf, " +
                  "CASE " +
                  "        WHEN C.courseTime = 1 THEN '1교시 (09:00~10:00)' " +
                  "        WHEN C.courseTime = 2 THEN '2교시 (10:00~11:00)' " +
                  "        WHEN C.courseTime = 3 THEN '3교시 (11:00~12:00)' " +
                  "        WHEN C.courseTime = 4 THEN '4교시 (12:00~13:00)' " +
                  "        WHEN C.courseTime = 5 THEN '5교시 (13:00~14:00)' " +
                  "        WHEN C.courseTime = 6 THEN '6교시 (14:00~15:00)' " +
                  "        WHEN C.courseTime = 7 THEN '7교시 (15:00~16:00)' " +
                  "        WHEN C.courseTime = 8 THEN '8교시 (16:00~17:00)' " +
                  "        WHEN C.courseTime = 9 THEN '9교시 (17:00~18:00)' " +
                  "        ELSE '1교시 (09:00~10:00)' " +
                  "    END AS formattedCourseTime " +
                  "FROM " +
                  "Course AS C " +
                  "INNER JOIN University AS U ON U.universityId = C.universityId " +
                  "INNER JOIN Building AS B ON B.buildingId = C.buildingId " +
                  "INNER JOIN Room AS R ON R.roomId = C.roomId " +
                  "WHERE C.universityId = :universityId AND " +
                  "C.courseTime IN :courseTime AND " +
                  "C.courseDayOf IN :courseDayOf AND " +
                  "C.buildingId = :buildingId " +
                  "ORDER BY C.courseDayOf, C.courseTime, B.buildingName, B.buildingNumber;",
          nativeQuery = true)
  <T> List<T> findByUniversityIdAndCourseTimeAndCourseDayOfAndBuildingId(@Param("universityId") Long universityId, @Param("courseTime") List<Long> courseTime, @Param("courseDayOf") List<Long> courseDayOf, @Param("buildingId") Long buildingId, Class<T> type);

  @Query(value =
          "SELECT DISTINCT " +
                  "R.roomId, " +
                  "B.buildingName, " +
                  "B.buildingNumber, " +
                  "R.roomNumber, " +
                  "C.courseTime, " +
                  "C.courseDayOf, " +
                  "CASE " +
                  "        WHEN C.courseDayOf = 1 THEN '월요일' " +
                  "        WHEN C.courseDayOf = 2 THEN '화요일' " +
                  "        WHEN C.courseDayOf = 3 THEN '수요일' " +
                  "        WHEN C.courseDayOf = 4 THEN '목요일' " +
                  "        WHEN C.courseDayOf = 5 THEN '금요일' " +
                  "        WHEN C.courseDayOf = 6 THEN '토요일' " +
                  "        WHEN C.courseDayOf = 0 THEN '일요일' " +
                  "        ELSE '월요일' " +
                  "    END AS formattedCourseDayOf, " +
                  "CASE " +
                  "        WHEN C.courseTime = 1 THEN '1교시 (09:00~10:00)' " +
                  "        WHEN C.courseTime = 2 THEN '2교시 (10:00~11:00)' " +
                  "        WHEN C.courseTime = 3 THEN '3교시 (11:00~12:00)' " +
                  "        WHEN C.courseTime = 4 THEN '4교시 (12:00~13:00)' " +
                  "        WHEN C.courseTime = 5 THEN '5교시 (13:00~14:00)' " +
                  "        WHEN C.courseTime = 6 THEN '6교시 (14:00~15:00)' " +
                  "        WHEN C.courseTime = 7 THEN '7교시 (15:00~16:00)' " +
                  "        WHEN C.courseTime = 8 THEN '8교시 (16:00~17:00)' " +
                  "        WHEN C.courseTime = 9 THEN '9교시 (17:00~18:00)' " +
                  "        ELSE '1교시 (09:00~10:00)' " +
                  "    END AS formattedCourseTime " +
                  "FROM " +
                  "Course AS C " +
                  "INNER JOIN University AS U ON U.universityId = C.universityId " +
                  "INNER JOIN Building AS B ON B.buildingId = C.buildingId " +
                  "INNER JOIN Room AS R ON R.roomId = C.roomId " +
                  "WHERE C.universityId = :universityId AND " +
                  "C.courseTime IN :courseTime AND " +
                  "C.courseDayOf IN :courseDayOf AND " +
                  "C.buildingId = :buildingId AND " +
                  "R.roomNumber LIKE %:roomName% " +
                  "ORDER BY C.courseDayOf, C.courseTime, B.buildingName, B.buildingNumber;",
          nativeQuery = true)
  <T> List<T> findByUniversityIdAndCourseTimeAndCourseDayOfAndRoomNameAndBuildingId(@Param("universityId") Long universityId, @Param("courseTime") List<Long> courseTime, @Param("courseDayOf") List<Long> courseDayOf, @Param("roomName") String roomName, @Param("buildingId") Long buildingId, Class<T> type);
}

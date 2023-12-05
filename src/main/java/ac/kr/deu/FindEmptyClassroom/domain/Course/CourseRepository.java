package ac.kr.deu.FindEmptyClassroom.domain.Course;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long> {
  <T> Optional<T> findByCourseId(Long courseId, Class<T> type);

  @Query(
    value = "SELECT DISTINCT " +
    "R.roomId, " +
    "R.roomNumber, " +
    "B.buildingName, " +
    "B.buildingNumber " +
    "FROM " +
    "Course AS C " +
    "INNER JOIN University AS U ON U.universityId = C.universityId " +
    "INNER JOIN Building AS B ON B.buildingId = C.buildingId " +
    "INNER JOIN Room AS R ON R.roomId = C.roomId " +
    "WHERE C.buildingId NOT IN(1, 2, 3, 6, 18, 23, 24, 25)" +
    "ORDER BY R.roomNumber, B.buildingName, B.buildingNumber;",
    nativeQuery = true
  )
  <T> List<T> findAll(Class<T> type);

  @Query(
          value = "SELECT DISTINCT " +
                  "R.roomId, " +
                  "R.roomNumber, " +
                  "B.buildingName, " +
                  "B.buildingNumber " +
                  "FROM " +
                  "Course AS C " +
                  "INNER JOIN University AS U ON U.universityId = C.universityId " +
                  "INNER JOIN Building AS B ON B.buildingId = C.buildingId " +
                  "INNER JOIN Room AS R ON R.roomId = C.roomId " +
                  "WHERE C.buildingId NOT IN(1, 2, 3, 6, 18, 23, 24, 25) AND " +
                  "C.buildingId = :buildingId " +
                  "ORDER BY R.roomNumber, B.buildingName, B.buildingNumber;",
          nativeQuery = true
  )
  <T> List<T> findAllByBuildingId(
          @Param("buildingId") Long buildingId,
          Class<T> type);

  @Query(
    value = "SELECT DISTINCT " +
            "R.roomId, " +
            "R.roomNumber, " +
            "B.buildingName, " +
            "B.buildingNumber " +
            "FROM " +
            "Course AS C " +
            "INNER JOIN University AS U ON U.universityId = C.universityId " +
            "INNER JOIN Building AS B ON B.buildingId = C.buildingId " +
            "INNER JOIN Room AS R ON R.roomId = C.roomId " +
            "WHERE C.buildingId NOT IN(1, 2, 3, 6, 18, 23, 24, 25) AND " +
            "R.roomNumber LIKE %:roomName% " +
            "ORDER BY R.roomNumber, B.buildingName, B.buildingNumber;",
    nativeQuery = true
  )
  <T> List<T> findAllByRoomName(
          @Param("roomName") String roomName,
          Class<T> type);

  @Query(
          value = "SELECT DISTINCT " +
                  "R.roomId, " +
                  "R.roomNumber, " +
                  "B.buildingName, " +
                  "B.buildingNumber " +
                  "FROM " +
                  "Course AS C " +
                  "INNER JOIN University AS U ON U.universityId = C.universityId " +
                  "INNER JOIN Building AS B ON B.buildingId = C.buildingId " +
                  "INNER JOIN Room AS R ON R.roomId = C.roomId " +
                  "WHERE C.buildingId NOT IN(1, 2, 3, 6, 18, 23, 24, 25) AND " +
                  "C.buildingId = :buildingId AND " +
                  "R.roomNumber LIKE %:roomName% " +
                  "ORDER BY R.roomNumber, B.buildingName, B.buildingNumber;",
          nativeQuery = true
  )
  <T> List<T> findAllByBuildingIdAndRoomName(
          @Param("buildingId") Long buildingId,
          @Param("roomName") String roomName,
          Class<T> type);

  @Query(
    value = "SELECT DISTINCT " +
    "R.roomId, " +
    "R.roomNumber, " +
    "B.buildingName, " +
    "B.buildingNumber " +
    "FROM " +
    "Course AS C " +
    "INNER JOIN University AS U ON U.universityId = C.universityId " +
    "INNER JOIN Building AS B ON B.buildingId = C.buildingId " +
    "INNER JOIN Room AS R ON R.roomId = C.roomId " +
    "WHERE C.universityId = :universityId AND " +
    "C.roomId NOT IN (" +
    "SELECT C2.roomId FROM Course AS C2 " +
    "WHERE C2.universityId = :universityId AND " +
    "C2.courseTime IN :courseTime AND " +
    "C2.courseDayOf IN :courseDayOf" +
    ") AND " +
    "C.buildingId NOT IN(1, 2, 3, 6, 18, 23, 24, 25)" +
    "ORDER BY R.roomNumber, B.buildingName, B.buildingNumber;",
    nativeQuery = true
  )
  <T> List<T> findByUniversityIdAndCourseTimeAndCourseDayOf(
    @Param("universityId") Long universityId,
    @Param("courseTime") List<Long> courseTime,
    @Param("courseDayOf") List<Long> courseDayOf,
    Class<T> type
  );

  @Query(
    value = "SELECT DISTINCT " +
    "R.roomId, " +
    "R.roomNumber, " +
    "B.buildingName, " +
    "B.buildingNumber " +
    "FROM " +
    "Course AS C " +
    "INNER JOIN University AS U ON U.universityId = C.universityId " +
    "INNER JOIN Building AS B ON B.buildingId = C.buildingId " +
    "INNER JOIN Room AS R ON R.roomId = C.roomId " +
    "WHERE C.universityId = :universityId AND " +
    "C.roomId NOT IN (" +
    "SELECT C2.roomId FROM Course AS C2 " +
    "WHERE C2.universityId = :universityId AND " +
    "C2.courseTime IN :courseTime AND " +
    "C2.courseDayOf IN :courseDayOf" +
    ") AND " +
    "C.buildingId NOT IN(1, 2, 3, 6, 18, 23, 24, 25) AND " +
    "R.roomNumber LIKE %:roomName% " +
    "ORDER BY R.roomNumber, B.buildingName, B.buildingNumber;",
    nativeQuery = true
  )
  <T> List<T> findByUniversityIdAndCourseTimeAndCourseDayOfAndRoomName(
    @Param("universityId") Long universityId,
    @Param("courseTime") List<Long> courseTime,
    @Param("courseDayOf") List<Long> courseDayOf,
    @Param("roomName") String roomName,
    Class<T> type
  );

  @Query(
    value = "SELECT DISTINCT " +
    "R.roomId, " +
    "R.roomNumber, " +
    "B.buildingName, " +
    "B.buildingNumber " +
    "FROM " +
    "Course AS C " +
    "INNER JOIN University AS U ON U.universityId = C.universityId " +
    "INNER JOIN Building AS B ON B.buildingId = C.buildingId " +
    "INNER JOIN Room AS R ON R.roomId = C.roomId " +
    "WHERE C.universityId = :universityId AND " +
    "C.roomId NOT IN (" +
    "SELECT C2.roomId FROM Course AS C2 " +
    "WHERE C2.universityId = :universityId AND " +
    "C2.courseTime IN :courseTime AND " +
    "C2.courseDayOf IN :courseDayOf" +
    ") AND " +
    "C.buildingId NOT IN(1, 2, 3, 6, 18, 23, 24, 25) AND " +
    "C.buildingId = :buildingId " +
    "ORDER BY R.roomNumber, B.buildingName, B.buildingNumber;",
    nativeQuery = true
  )
  <T> List<T> findByUniversityIdAndCourseTimeAndCourseDayOfAndBuildingId(
    @Param("universityId") Long universityId,
    @Param("courseTime") List<Long> courseTime,
    @Param("courseDayOf") List<Long> courseDayOf,
    @Param("buildingId") Long buildingId,
    Class<T> type
  );

  @Query(
    value = "SELECT DISTINCT " +
    "R.roomId, " +
    "R.roomNumber, " +
    "B.buildingName, " +
    "B.buildingNumber " +
    "FROM " +
    "Course AS C " +
    "INNER JOIN University AS U ON U.universityId = C.universityId " +
    "INNER JOIN Building AS B ON B.buildingId = C.buildingId " +
    "INNER JOIN Room AS R ON R.roomId = C.roomId " +
    "WHERE C.universityId = :universityId AND " +
    "C.roomId NOT IN (" +
    "SELECT C2.roomId FROM Course AS C2 " +
    "WHERE C2.universityId = :universityId AND " +
    "C2.courseTime IN :courseTime AND " +
    "C2.courseDayOf IN :courseDayOf" +
    ") AND " +
    "C.buildingId NOT IN(1, 2, 3, 6, 18, 23, 24, 25) AND " +
    "C.buildingId = :buildingId AND " +
    "R.roomNumber LIKE %:roomName% " +
    "ORDER BY R.roomNumber, B.buildingName, B.buildingNumber;",
    nativeQuery = true
  )
  <
    T
  > List<T> findByUniversityIdAndCourseTimeAndCourseDayOfAndRoomNameAndBuildingId(
    @Param("universityId") Long universityId,
    @Param("courseTime") List<Long> courseTime,
    @Param("courseDayOf") List<Long> courseDayOf,
    @Param("roomName") String roomName,
    @Param("buildingId") Long buildingId,
    Class<T> type
  );
}

package ac.kr.deu.FindEmptyClassroom.domain.Course.dto;

public interface SearchMapping {
    Long getRoomId();

    String getBuildingName();

    String getBuildingNumber();

    String getRoomNumber();

    String getFormattedCourseDayOf();

    String getFormattedCourseTime();
}

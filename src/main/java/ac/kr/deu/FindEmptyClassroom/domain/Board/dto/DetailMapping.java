package ac.kr.deu.FindEmptyClassroom.domain.Board.dto;

import java.time.LocalDateTime;

public interface DetailMapping {
    Long getBoardId();

    Long getViewCount();

    String getTitle();

    String getBuildingName();

    String getBuildingNumber();

    String getRoomNumber();

    LocalDateTime getCreatedAt();
}

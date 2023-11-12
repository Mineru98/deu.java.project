package ac.kr.deu.FindEmptyClassroom.domain.Req;

import lombok.Data;

@Data
public class RequestDTO {
    private Long universityId;

    private Long courseDayOf;

    private Long courseTime;

    private String roomName;

    private Long buildingId;
}

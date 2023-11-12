package ac.kr.deu.FindEmptyClassroom.domain.Req;

import lombok.Data;

import java.util.List;

@Data
public class RequestDTO {
    private Long universityId;

    private List<Long> courseDayOf;

    private List<Long> courseTime;

    private String roomName;

    private Long buildingId;
}

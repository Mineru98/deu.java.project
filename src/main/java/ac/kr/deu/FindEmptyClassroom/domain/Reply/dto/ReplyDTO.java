package ac.kr.deu.FindEmptyClassroom.domain.Reply.dto;

import lombok.Data;

@Data
public class ReplyDTO {
    private Long userId;

    private Long roomId;

    private String replyContent;
}

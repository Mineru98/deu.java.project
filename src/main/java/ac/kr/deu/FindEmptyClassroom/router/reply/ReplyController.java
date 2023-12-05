package ac.kr.deu.FindEmptyClassroom.router.reply;

import ac.kr.deu.FindEmptyClassroom.domain.LikeReply.dto.LikeDTO;
import ac.kr.deu.FindEmptyClassroom.domain.Reply.dto.ReplyDTO;
import ac.kr.deu.FindEmptyClassroom.enums.ResponseState;
import ac.kr.deu.FindEmptyClassroom.handler.exception.ResponseDTO;
import ac.kr.deu.FindEmptyClassroom.service.reply.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/reply")
public class ReplyController {
    private final ReplyService replyService;

    @Tag(name = "댓글")
    @Operation(summary = "댓글 작성", description = "댓글 작성")
    @PostMapping("")
    public ResponseEntity<?> create(
            HttpServletRequest request,
            @RequestBody(required = true) ReplyDTO dto
    ) {
        replyService.createReply(dto);
        return new ResponseEntity<>(
                new ResponseDTO<>(ResponseState.CREATED),
                HttpStatus.CREATED
        );
    }

    @Tag(name = "댓글")
    @Operation(summary = "댓글 반응", description = "댓글 반응")
    @PostMapping("/like")
    public ResponseEntity<?> replyReaction(
            HttpServletRequest request,
            @RequestBody(required = true) LikeDTO dto
    ) {
        replyService.likeAndUnLike(dto);
        return new ResponseEntity<>(
                new ResponseDTO<>(ResponseState.CREATED),
                HttpStatus.CREATED
        );
    }
}

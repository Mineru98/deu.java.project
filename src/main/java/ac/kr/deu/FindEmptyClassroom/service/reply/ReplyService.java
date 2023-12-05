package ac.kr.deu.FindEmptyClassroom.service.reply;

import ac.kr.deu.FindEmptyClassroom.domain.LikeReply.LikeReply;
import ac.kr.deu.FindEmptyClassroom.domain.LikeReply.LikeReplyRepository;
import ac.kr.deu.FindEmptyClassroom.domain.LikeReply.dto.LikeDTO;
import ac.kr.deu.FindEmptyClassroom.domain.Reply.Reply;
import ac.kr.deu.FindEmptyClassroom.domain.Reply.ReplyRepository;
import ac.kr.deu.FindEmptyClassroom.domain.Reply.dto.ReplyDTO;
import ac.kr.deu.FindEmptyClassroom.domain.Room.Room;
import ac.kr.deu.FindEmptyClassroom.domain.Room.RoomRepository;
import ac.kr.deu.FindEmptyClassroom.domain.User.User;
import ac.kr.deu.FindEmptyClassroom.domain.User.UserRepository;
import ac.kr.deu.FindEmptyClassroom.handler.exception.CustomIllegalStateExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReplyRepository replyRepository;
    private final LikeReplyRepository likeReplyRepository;

    @Transactional(readOnly = true)
    public List<Reply> getAllByRoomId(Long roomId) {
        Optional<Room> roomEntity = roomRepository.findById(roomId);
        if (roomEntity.isPresent()) {
            return replyRepository.findAllByBoard(roomEntity.get().getBoard());
        } else {
            throw new CustomIllegalStateExceptionHandler(
                    "존재하지 않는 게시글입니다"
            );
        }
    }

    @Transactional
    public void createReply(ReplyDTO dto) {
        Reply replyEntity = new Reply();
        replyEntity.setContent(dto.getReplyContent());
        Optional<User> userEntity = userRepository.findById(dto.getUserId());
        Optional<Room> roomEntity = roomRepository.findById(dto.getRoomId());
        if (userEntity.isPresent() && roomEntity.isPresent()) {
            replyEntity.setBoard(roomEntity.get().getBoard());
            replyEntity.setUser(userEntity.get());
            replyRepository.save(replyEntity);
        } else {
            throw new CustomIllegalStateExceptionHandler(
                    "존재하지 않는 게시글입니다"
            );
        }
    }

    @Transactional
    public void likeAndUnLike(LikeDTO dto) {
        Optional<Reply> replyEntity = replyRepository.findById(dto.getReplyId());
        if (replyEntity.isPresent()) {
            if (replyEntity.get().getUser().getUserId().equals(dto.getUserId())) {
                throw new CustomIllegalStateExceptionHandler(
                        "자신의 댓글에는 추천할 수 없습니다."
                );
            }
        }
        Long count = likeReplyRepository.countByReplyIdAndUserId(dto.getReplyId(), dto.getUserId());
        if (count == 0) {
            // 좋아요 실행
            LikeReply entity = new LikeReply();
            Optional<User> userEntity = userRepository.findById(dto.getUserId());
            if (replyEntity.isPresent() && userEntity.isPresent()) {
                entity.setReply(replyEntity.get());
                entity.setUser(userEntity.get());
                likeReplyRepository.save(entity);
            } else {
                throw new CustomIllegalStateExceptionHandler(
                        "존재하지 않는 댓글입니다"
                );
            }
        } else {
            // 삭제
            likeReplyRepository.deleteByReplyIdAndUserId(dto.getReplyId(), dto.getUserId());
        }
    }
}

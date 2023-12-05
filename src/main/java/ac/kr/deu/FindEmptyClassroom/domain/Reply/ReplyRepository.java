package ac.kr.deu.FindEmptyClassroom.domain.Reply;

import java.util.List;
import java.util.Optional;

import ac.kr.deu.FindEmptyClassroom.domain.Board.Board;
import ac.kr.deu.FindEmptyClassroom.domain.Room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
  <T> Optional<T> findByReplyId(Long replyId, Class<T> type);

  <T> List<T> findAllByBoard(Board board);

  void deleteByReplyId(Long replyId);
}

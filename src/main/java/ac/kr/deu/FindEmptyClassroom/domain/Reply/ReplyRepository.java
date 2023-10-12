package ac.kr.deu.FindEmptyClassroom.domain.Reply;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
  <T> Optional<T> findByReplyId(Long replyId, Class<T> type);

  void deleteByReplyId(Long replyId);
}

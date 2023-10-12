package ac.kr.deu.FindEmptyClassroom.domain.LikeReply;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeReplyRepository extends JpaRepository<LikeReply, Long> {
  <T> Optional<T> findByLikeId(Long likeId, Class<T> type);

  void deleteByLikeId(Long likeId);
}

package ac.kr.deu.FindEmptyClassroom.domain.LikeComment;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeCommentRepository
  extends JpaRepository<LikeComment, Long> {
  <T> Optional<T> findById(Long id, Class<T> type);

  void deleteById(Long id);
}

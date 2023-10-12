package ac.kr.deu.FindEmptyClassroom.domain.Board;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
  <T> Optional<T> findByBoardId(Long boardId, Class<T> type);

  void deleteByBoardId(Long boardId);
}

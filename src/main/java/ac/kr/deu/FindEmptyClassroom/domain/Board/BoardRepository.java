package ac.kr.deu.FindEmptyClassroom.domain.Board;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {
  <T> Optional<T> findByBoardId(Long boardId, Class<T> type);

  @Query(
          value = "SELECT " +
                  "B.boardId, " +
                  "B.viewCount, " +
                  "B.title, " +
                  "B.createdAt, " +
                  "R.roomNumber, " +
                  "BD.buildingName, " +
                  "BD.buildingNumber " +
                  "FROM Board AS B " +
                  "JOIN Room AS R ON R.roomId =B.roomId " +
                  "JOIN Building AS BD ON BD.buildingId = R.buildingId " +
                  "WHERE B.roomId = :roomId",
          nativeQuery = true
  )
  <T> Optional<T> findByRoomId(
          @Param("roomId") Long roomId,
          Class<T> type
  );

  void deleteByBoardId(Long boardId);
}

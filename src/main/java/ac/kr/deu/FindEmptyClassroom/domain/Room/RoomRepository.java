package ac.kr.deu.FindEmptyClassroom.domain.Room;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
  <T> Optional<T> findByRoomId(Long roomId, Class<T> type);

  void deleteByRoomId(Long roomId);
}

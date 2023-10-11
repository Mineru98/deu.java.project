package ac.kr.deu.FindEmptyClassroom.domain.Room;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
  <T> Optional<T> findById(Long id, Class<T> type);

  void deleteById(Long id);
}

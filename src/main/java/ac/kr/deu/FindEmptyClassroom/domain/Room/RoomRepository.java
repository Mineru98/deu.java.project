package ac.kr.deu.FindEmptyClassroom.domain.Room;

import java.util.List;
import java.util.Optional;

import ac.kr.deu.FindEmptyClassroom.domain.University.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
  <T> Optional<T> findByRoomId(Long roomId, Class<T> type);

  List<Room> findAllByUniversity(University university);

  void deleteByRoomId(Long roomId);
}

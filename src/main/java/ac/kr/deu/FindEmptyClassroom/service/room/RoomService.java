package ac.kr.deu.FindEmptyClassroom.service.room;

import ac.kr.deu.FindEmptyClassroom.domain.Room.Room;
import ac.kr.deu.FindEmptyClassroom.domain.Room.RoomRepository;
import ac.kr.deu.FindEmptyClassroom.domain.University.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final UniversityRepository universityRepository;

    @Transactional
    public Room getOneByRoomId(Long roomId) {
        Optional<Room> entity = roomRepository.findByRoomId(roomId, Room.class);
        if (entity.isPresent()) {
            return entity.get();
        } else {
            return null;
        }
    }
}

package ac.kr.deu.FindEmptyClassroom.service.room;

import ac.kr.deu.FindEmptyClassroom.domain.Room.Room;
import ac.kr.deu.FindEmptyClassroom.domain.Room.RoomRepository;
import ac.kr.deu.FindEmptyClassroom.handler.exception.CustomIllegalStateExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    @Transactional
    public Room getOneByRoomId(Long roomId) {
        Optional<Room> entity = roomRepository.findByRoomId(roomId, Room.class);
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new CustomIllegalStateExceptionHandler(
                "존재하지 않는 게시글입니다."
            );
        }
    }
}

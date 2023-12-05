package ac.kr.deu.FindEmptyClassroom.service.board;

import ac.kr.deu.FindEmptyClassroom.domain.Board.BoardRepository;
import ac.kr.deu.FindEmptyClassroom.domain.BoardLog.BoardLog;
import ac.kr.deu.FindEmptyClassroom.domain.BoardLog.BoardLogRepository;
import ac.kr.deu.FindEmptyClassroom.domain.Board.dto.DetailMapping;
import ac.kr.deu.FindEmptyClassroom.handler.exception.CustomIllegalStateExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardLogRepository boardLogRepository;

    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null) {
            ipAddress = request.getHeader("X-Real-IP");
        }
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    @Transactional
    public DetailMapping getByRoomId(Long roomId, HttpServletRequest request) {
        Optional<DetailMapping> entity = boardRepository.findByRoomId(roomId, DetailMapping.class);
        if (entity.isPresent()) {
            BoardLog logEntity = new BoardLog();
            boardRepository.findById(entity.get().getBoardId()).ifPresent(b -> {
                logEntity.setBoard(b);
                logEntity.setIp(getIpAddress(request));
                boardLogRepository.save(logEntity);
                b.setViewCount(b.getViewCount() + 1);
            });
            return entity.get();
        } else {
            throw new CustomIllegalStateExceptionHandler(
                "존재하지 않는 게시글입니다."
            );
        }
    }
}

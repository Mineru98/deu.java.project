package ac.kr.deu.FindEmptyClassroom.service.user;

import ac.kr.deu.FindEmptyClassroom.domain.LikeReply.LikeReplyRepository;
import ac.kr.deu.FindEmptyClassroom.domain.LikeReply.dto.RankMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final LikeReplyRepository likeReplyRepository;

    @Transactional(readOnly = true)
    public List<RankMapping> getRank() {
        return likeReplyRepository.findAllWithRank(RankMapping.class);
    }
}

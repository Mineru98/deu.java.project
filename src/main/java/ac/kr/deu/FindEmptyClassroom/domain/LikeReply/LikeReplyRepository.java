package ac.kr.deu.FindEmptyClassroom.domain.LikeReply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeReplyRepository extends JpaRepository<LikeReply, Long> {
    <T> Optional<T> findByLikeId(Long likeId, Class<T> type);

    @Query(
            value = "SELECT COUNT(*) AS CNT FROM " +
                    "LikeReply AS LR " +
                    "WHERE LR.replyId = :replyId AND LR.userId = :userId",
            nativeQuery = true
    )
    Long countByReplyIdAndUserId(@Param("replyId") Long replyId, @Param("userId") Long userId);

    @Query(
            value = "DELETE FROM LikeReply AS LR " +
                    "WHERE LR.replyId = :replyId AND LR.userId = :userId",
            nativeQuery = true
    )
    void deleteByReplyIdAndUserId(@Param("replyId") Long replyId, @Param("userId") Long userId);

    void deleteByLikeId(Long likeId);
}

package ac.kr.deu.FindEmptyClassroom.domain.LikeReply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeReplyRepository extends JpaRepository<LikeReply, Long> {
    <T> Optional<T> findByLikeId(Long likeId, Class<T> type);

    @Query(value = "SELECT " +
            "U.userId, " +
            "U.username, " +
            "COUNT(L.replyId) AS likeCount " +
            "FROM `User` U " +
            "JOIN LikeReply L ON U.userId = L.userId " +
            "GROUP BY U.userId, U.username " +
            "ORDER BY likeCount DESC", nativeQuery = true)
    <T> List<T> findAllWithRank(Class<T> type);

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

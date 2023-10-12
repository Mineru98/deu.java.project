package ac.kr.deu.FindEmptyClassroom.domain.User;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  <T> List<T> findAllByUsernameContaining(String username, Class<T> type);

  <T> Optional<T> findByUsername(String username, Class<T> type);

  Long countByUsername(String username);

  Optional<User> findByUsernameAndPassword(String username, String password);

  <T> Optional<T> findByUserId(Long userId, Class<T> type);

  void deleteByUserId(Long userId);
}

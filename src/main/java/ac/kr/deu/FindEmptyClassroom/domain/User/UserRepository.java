package ac.kr.deu.FindEmptyClassroom.domain.User;

import ac.kr.deu.FindEmptyClassroom.domain.University.University;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  <T> List<T> findAllByUsernameContaining(String username, Class<T> type);

  <T> Optional<T> findByUsername(String username, Class<T> type);

  Long countByUsername(String username);

  Long countByUsernameAndUniversity(String username, University university);

  Optional<User> findByUsernameAndPassword(String username, String password);

  Optional<User> findByUsernameAndPasswordAndUniversity(
    String username,
    String password,
    University university
  );

  <T> Optional<T> findByUserId(Long userId, Class<T> type);

  void deleteByUserId(Long userId);
}

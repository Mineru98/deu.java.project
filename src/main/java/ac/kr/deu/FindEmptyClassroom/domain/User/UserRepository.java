package ac.kr.deu.FindEmptyClassroom.domain.User;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  <T> List<T> findAllByNameContaining(String Name, Class<T> type);

  <T> Optional<T> findByUsername(String username, Class<T> type);

  Long countByUsername(String username);

  Optional<User> findByUsernameAndPassword(String username, String password);

  <T> Optional<T> findById(Long id, Class<T> type);

  void deleteById(Long id);
}

package ac.kr.deu.FindEmptyClassroom.service.auth;

import ac.kr.deu.FindEmptyClassroom.domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
}

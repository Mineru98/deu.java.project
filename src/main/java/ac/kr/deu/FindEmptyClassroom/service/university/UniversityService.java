package ac.kr.deu.FindEmptyClassroom.service.university;

import ac.kr.deu.FindEmptyClassroom.domain.University.University;
import ac.kr.deu.FindEmptyClassroom.domain.University.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityService {
    private final UniversityRepository universityRepository;

    @Transactional(readOnly = true)
    public List<University> getAll() {
        return universityRepository.findAll();
    };
}
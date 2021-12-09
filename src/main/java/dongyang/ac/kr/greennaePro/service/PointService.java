package dongyang.ac.kr.greennaePro.service;

import dongyang.ac.kr.greennaePro.domain.Point;
import dongyang.ac.kr.greennaePro.domain.User;
import dongyang.ac.kr.greennaePro.dto.PointDto;
import dongyang.ac.kr.greennaePro.repository.PointRepository;
import dongyang.ac.kr.greennaePro.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PointService {
    private final UserRepository userRepository;
    private final PointRepository pointRepository;


    @Transactional
    public void savePoint(PointDto dto,Long id){
        User user = userRepository.findById(id).get();
        dto.setUser(user);
        user.setRole("ROLE_MEMBER");
        pointRepository.save(dto.toEntity());
    }
}

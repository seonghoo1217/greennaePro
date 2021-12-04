package dongyang.ac.kr.greennaePro.repository.user;

import dongyang.ac.kr.greennaePro.domain.User;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCustomRepository {
    PageImpl<User> userpaging(Long id, Pageable pageable);
}

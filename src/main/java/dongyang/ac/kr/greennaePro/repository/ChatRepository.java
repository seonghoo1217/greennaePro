package dongyang.ac.kr.greennaePro.repository;

import dongyang.ac.kr.greennaePro.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {
}

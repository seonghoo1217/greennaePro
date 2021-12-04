package dongyang.ac.kr.greennaePro.repository.board;

import dongyang.ac.kr.greennaePro.domain.Board;
import dongyang.ac.kr.greennaePro.domain.User;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long>,BoardCustomRepository {

    PageImpl<Board> getBoardList(Long id, Pageable pageable);

}

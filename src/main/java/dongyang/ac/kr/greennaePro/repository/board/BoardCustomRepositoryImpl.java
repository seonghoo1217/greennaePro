package dongyang.ac.kr.greennaePro.repository.board;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dongyang.ac.kr.greennaePro.domain.Board;
import dongyang.ac.kr.greennaePro.domain.User;
import dongyang.ac.kr.greennaePro.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

import static dongyang.ac.kr.greennaePro.domain.QBoard.board;
import static dongyang.ac.kr.greennaePro.domain.QUser.user;


public class BoardCustomRepositoryImpl implements BoardCustomRepository {

    private final JPAQueryFactory queryFactory;

    public BoardCustomRepositoryImpl(EntityManager em){
        this.queryFactory=new JPAQueryFactory(em);
    }

    @Override
    public PageImpl<Board> getBoardList(Long id, Pageable pageable) {
        QueryResults<Board> boardResults = queryFactory
                .selectFrom(board)
                .leftJoin(user).on(user.id.eq(board.user.id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(board.createDate.desc())
                .fetchResults();

        List<BoardDto> ListBoardResult = boardResults.getResults().stream().map(Board::toDto).collect(Collectors.toList());

        long total = boardResults.getTotal();

        return new PageImpl(ListBoardResult,pageable,total);
    }
}

package dongyang.ac.kr.greennaePro.service;

import dongyang.ac.kr.greennaePro.domain.Board;
import dongyang.ac.kr.greennaePro.dto.BoardDto;
import dongyang.ac.kr.greennaePro.principal.AccountContext;
import dongyang.ac.kr.greennaePro.repository.board.BoardRepository;
import dongyang.ac.kr.greennaePro.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BoardService {
    public BoardDto readBoard(Long id){
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessError("[board_id=" + id+ "] 해당 게시글이 존재하지 않습니다."));
        return board.toDto();
    }

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;
    @Transactional
    public void boardSave(BoardDto boardDto, @AuthenticationPrincipal AccountContext accountContext){
        Board board = boardDto.toEntity();
        board.setCreateDate(LocalDateTime.now());
        board.setWriter(accountContext.getUsername());
        board.setUser(accountContext.returnUser());
        boardRepository.save(board);
    }

    @Transactional
    public Long boardUpdate(Long id, Board modiBoard){
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        board.setContent(modiBoard.getContent());

        return board.getId();
    }

    @Transactional
    public void delete(Long id){
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessError("[board_id=" + id + "] 해당 게시글이 존재하지 않습니다."));
        boardRepository.delete(board);
    }
}

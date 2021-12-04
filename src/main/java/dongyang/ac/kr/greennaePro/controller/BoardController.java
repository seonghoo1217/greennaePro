package dongyang.ac.kr.greennaePro.controller;


import dongyang.ac.kr.greennaePro.domain.Board;
import dongyang.ac.kr.greennaePro.dto.BoardDto;
import dongyang.ac.kr.greennaePro.dto.PageDto;
import dongyang.ac.kr.greennaePro.principal.AccountContext;
import dongyang.ac.kr.greennaePro.repository.board.BoardRepository;
import dongyang.ac.kr.greennaePro.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
    private final BoardService boardService;

    private final BoardRepository boardRepository;


    @GetMapping("/boardCreate")
    public String boardCreateForm(Model model, BoardDto dto,
                                  @AuthenticationPrincipal AccountContext accountContext){

        log.info("boardCreate 호출");
        model.addAttribute("dto",dto);
        model.addAttribute("accountContext",accountContext);
        log.info("accountContext={}", accountContext);

        return "/board/boardCreate";
    }
    @PostMapping("/boardCreate")
    public String boardCreate(Model model,BoardDto dto,@AuthenticationPrincipal AccountContext accountContext) throws UnsupportedEncodingException {


        log.info("boardCreate");
        boardService.boardSave(dto,accountContext);

        return "redirect:/board/boardList";
    }
    @GetMapping("/{id}/read")
    public String readBoard(@PathVariable("id")Long id,
                            @AuthenticationPrincipal AccountContext accountContext,
                            Model model,Board board) {
        BoardDto boardDto = boardService.readBoard(id);
        model.addAttribute("customboard",board);
        model.addAttribute("boardDto",boardDto);
        model.addAttribute("accountContext",accountContext);
        return "board/boardRead";
    }

    @PostMapping("/{id}/read")
    public String readBoardUpdate(@PathVariable("id")Long id){

        log.info("boardupdat용 readpost");

        return "redirect:/board/"+id+"/boardUpdate";
    }

    @GetMapping("/{id}/boardUpdate")
    public String updateBoard(@PathVariable("id")Long id,
                              @AuthenticationPrincipal AccountContext accountContext,
                              Model model,BoardDto dto) {
        log.info("update호출");
        model.addAttribute("dto",dto);
        model.addAttribute("accountContext",accountContext);

        return "board/boardUpdate";
    }
    @PostMapping("/{id}/boardUpdate")
    public String update(@PathVariable("id")Long id,
                         Board board){
        log.info("board--Board={}", board.getId());

        boardService.boardUpdate(id, board);
        return "redirect:/board/read/"+id;
    }

    @GetMapping("/boardList")
    public String boardList(Model model,@AuthenticationPrincipal AccountContext accountContext,
                            @PageableDefault(size = 16,sort = "createdDate",direction = Sort.Direction.DESC) Pageable pageable){

        log.info("board List 호출");

        PageImpl<Board> results = boardRepository.getBoardList(accountContext.getId(), pageable);
        model.addAttribute("board",results);
        model.addAttribute("page",new PageDto(results.getTotalElements(),pageable));
        model.addAttribute("accountContext", accountContext);

        return "board/boardList";
    }
}

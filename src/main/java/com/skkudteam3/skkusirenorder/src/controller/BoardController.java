package com.skkudteam3.skkusirenorder.src.controller;

import com.skkudteam3.skkusirenorder.src.dto.BoardDTO;
import com.skkudteam3.skkusirenorder.src.entity.Board;
import com.skkudteam3.skkusirenorder.src.repository.BoardRepository;
import com.skkudteam3.skkusirenorder.src.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    //private final MemberRepository memberRepository;

    @GetMapping("/boardForm")
    public String addBoard() {
        return "/board/boardForm";
    }

    //
    @PostMapping("/boardForm")
    public String createBoard(@ModelAttribute BoardDTO boardDto, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();

        boardDto.setCreatedBy(username);
        boardService.saveBoard(boardDto);

        return "redirect:/";
    }


    @GetMapping("/boardList")
    public String boardList(Model model, @PageableDefault(size = 10) Pageable pageable,
                            @RequestParam(required = false, defaultValue = "") String searchText) {

        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);

        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 1);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 3);

        model.addAttribute("boards", boards);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/boardList";
    }

    @GetMapping("/boardContent/{id}")
    public String boardContent(@PathVariable("id") Long id, Model model) {
        Board board = boardRepository.findById(id).get();
        model.addAttribute(board);

        return "/board/boardContent";
    }

    @PostMapping("/boardContent/{id}")
    public String addComment(@PathVariable("id") Long id, Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();

        Board board = boardRepository.findById(id).get();
         //member = memberRepository.findByUsername(username);


        LocalDateTime now = LocalDateTime.now();

/*
        BoardCommentDto boardCommentDtoSet = BoardCommentDto.builder()
                .created_by(username)
                .created_date(now)
                .delete_check('N')
                .member(member)
                .board(board)
                .build();
        boardCommentDto = boardCommentDtoSet;


        boardCommentService.saveBoardComment(boardCommentDto);

        List<BoardComment> comments = boardCommentRepository.findCommentsBoardId(id);

        model.addAttribute("comments", comments);*///
        model.addAttribute(board);
        return "/board/boardContent";
    }
}

package com.skkudteam3.skkusirenorder.src.service;

import com.skkudteam3.skkusirenorder.src.dto.BoardDTO;
import com.skkudteam3.skkusirenorder.src.entity.Board;
import com.skkudteam3.skkusirenorder.src.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;

    private ThreadLocal<Long> countVisitStore = new ThreadLocal<>();

    @Transactional
    public Long saveBoard(BoardDTO boardDto) {
        boardRepository.save(boardDto.toEntity());
        return boardDto.getId();
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Transactional
    public Page<Board> getBoardList(Pageable pageable) {

        return boardRepository.findAll(pageable);

    }

    public Page<Board> paging(int page) {
        return boardRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id")));

    }

    public Board findById(Long id){
        Board board = boardRepository.findById(id).get();
        return board;
    }


}
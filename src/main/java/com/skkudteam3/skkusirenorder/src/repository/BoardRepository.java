package com.skkudteam3.skkusirenorder.src.repository;

import com.skkudteam3.skkusirenorder.src.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAll();

    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

//        Board findById();
}
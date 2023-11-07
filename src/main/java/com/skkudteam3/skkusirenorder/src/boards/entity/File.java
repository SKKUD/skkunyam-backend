package com.skkudteam3.skkusirenorder.src.boards.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "FILE")
public class File {
    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String file_name;
    private String file_path;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

}

package com.skkudteam3.skkusirenorder.src.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "BOARD")
public class Board {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String createdBy;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    @OneToOne
    @JoinColumn(name = "admin_login_id")
    private AdminLogin adminLogin;

    @OneToMany(mappedBy = "board")
    private List<File> files = new ArrayList<>();


    @Builder
    public Board(String title, String content, String createdBy, Long countVisit ) {
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
    }
}

package com.skkudteam3.skkusirenorder.src.boards.entity;

import com.skkudteam3.skkusirenorder.src.admin.entity.AdminLogin;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    @OneToOne
    @JoinColumn(name = "admin_login_id")
    private AdminLogin adminLogin;

    @OneToMany(mappedBy = "board")
    private List<File> files = new ArrayList<>();



}

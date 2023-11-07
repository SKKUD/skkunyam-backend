package com.skkudteam3.skkusirenorder.src.admin.entity;


import com.skkudteam3.skkusirenorder.src.boards.entity.Board;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ADMIN_LOGIN")
public class AdminLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_login_id")
    private Long id;
    private String email;

    @OneToOne(mappedBy = "adminLogin")
    private Board board;
}

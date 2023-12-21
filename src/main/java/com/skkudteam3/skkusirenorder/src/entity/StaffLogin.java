package com.skkudteam3.skkusirenorder.src.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StaffLogin {

    @Id
    @Column(name = "staff_login_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    @OneToOne
    @JoinColumn(name = "cafeteria_id")
    private Cafeteria cafeteria;

}

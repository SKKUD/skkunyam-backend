package com.skkudteam3.skkusirenorder.src.cafeteria.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOptionDetail {

    @Id
    @Column(name = "menu_option_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String menuOptionDetailName;
    private String menuOptionDetailPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_option_group_id")
    private MenuOptionGroup menuOptionGroup;
}

package com.skkudteam3.skkusirenorder.src.cafeteria.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOptionGroup {

    @Id
    @Column(name = "menu_option_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String menuOptionGroupName;
    private Boolean isEssentialOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @OneToMany(mappedBy = "menuOptionGroup")
    private List<MenuOptionDetail> menuOptionDetails = new ArrayList<>();
}

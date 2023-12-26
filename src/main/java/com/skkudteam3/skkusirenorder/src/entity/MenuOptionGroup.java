package com.skkudteam3.skkusirenorder.src.entity;

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

    private String name;
    private Boolean isEssentialOption;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // 일대다 단방향
    @JoinColumn(name = "menu_option_group_id")
    private List<MenuOptionDetail> menuOptionDetails = new ArrayList<>();

    public MenuOptionGroup(String name, Boolean isEssentialOption, List<MenuOptionDetail> menuOptionDetails) {
        this.name = name;
        this.isEssentialOption = isEssentialOption;
        this.menuOptionDetails = menuOptionDetails;
    }

}

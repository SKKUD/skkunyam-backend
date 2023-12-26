package com.skkudteam3.skkusirenorder.src.entity;

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

    private String name;
    private int price;
    public MenuOptionDetail(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

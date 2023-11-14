package com.skkudteam3.skkusirenorder.src.cafeteria.entity;

import com.skkudteam3.skkusirenorder.src.payment.entity.Orders;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cafeteria {

    @Id
    @Column(name = "cafetria_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cafetria_description;
    private String cafeteria_name;
    private String location; // custom data type임,,,,

    @Enumerated(EnumType.STRING)
    private CafeteriaOpenStatus is_cafeteria_open;

    @OneToMany(mappedBy = "cafeteria")
    private List<CafeteriaImage> cafeteriaImages = new ArrayList<>(); // null 방지용.

    @OneToMany(mappedBy = "cafeteria")
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(mappedBy = "cafeteria")
    private List<Orders> orders = new ArrayList<>();

}

package com.skkudteam3.skkusirenorder.src.cafeteria.entity;

import com.skkudteam3.skkusirenorder.src.payment.entity.OrderMenu;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String menuName;
    private String menuDescription;
    private int menuPrice;
    private String menuCategory;
    private Boolean isBest;
    private Boolean isSeason;

    @OneToMany(mappedBy = "menu")
    private List<MenuOptionGroup> menuOptionGroups = new ArrayList<>();

    @OneToMany(mappedBy = "menu")
    private List<OrderMenu> orderMenus = new ArrayList<>();


    @OneToOne
    @JoinColumn(name = "menu_image_id")
    private MenuImage menuImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafeteria_id")
    private Cafeteria cafeteria;


}

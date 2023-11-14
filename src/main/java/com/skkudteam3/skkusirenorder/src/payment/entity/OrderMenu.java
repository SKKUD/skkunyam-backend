package com.skkudteam3.skkusirenorder.src.payment.entity;

import com.skkudteam3.skkusirenorder.src.cafeteria.entity.Cafeteria;
import com.skkudteam3.skkusirenorder.src.cafeteria.entity.Menu;
import com.skkudteam3.skkusirenorder.src.cafeteria.entity.MenuImage;
import com.skkudteam3.skkusirenorder.src.cafeteria.entity.MenuOptionGroup;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderMenu {

    @Id
    @Column(name = "order_menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @OneToMany(mappedBy = "orderMenu")
    private List<OrderMenuOptionGroup> orderMenuOptionGroups = new ArrayList<>();

}

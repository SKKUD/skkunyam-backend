package com.skkudteam3.skkusirenorder.src.payment.entity;

import com.skkudteam3.skkusirenorder.src.cafeteria.entity.Menu;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderMenuOptionGroup {

    @Id
    @Column(name = "order_menu_option_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderMenuOptionGroupName;

    @OneToMany(mappedBy = "orderMenuOptionGroup")
    private List<OrderMenuOptionDetail> orderMenuOptionDetails = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_menu_id")
    private OrderMenu orderMenu;
}

package com.skkudteam3.skkusirenorder.src.payment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderMenuOptionDetail {

    @Id
    @Column(name = "order_menu_option_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_menu_option_group_id")
    private OrderMenuOptionGroup orderMenuOptionGroup;
}

package com.skkudteam3.skkusirenorder.src.payment.entity;

import com.skkudteam3.skkusirenorder.src.cafeteria.entity.Cafeteria;
import com.skkudteam3.skkusirenorder.src.cafeteria.entity.MenuImage;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OrderStatus orderStatus;
    private LocalDateTime createdAt;
    private Boolean isTakeOut;

    // 고객id와 매핑 필요
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id")
//    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafeteria_id")
    private Cafeteria cafeteria;

    @OneToOne
    @JoinColumn(name = "order_menu_id")
    private OrderMenu orderMenu;


}

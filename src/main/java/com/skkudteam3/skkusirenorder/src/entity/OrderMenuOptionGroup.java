package com.skkudteam3.skkusirenorder.src.entity;

import com.skkudteam3.skkusirenorder.src.dto.OrderGetResDTO;
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

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_menu_option_group_id")
    private List<OrderMenuOptionDetail> orderMenuOptionDetails = new ArrayList<>();

    public OrderMenuOptionGroup(String name, List<OrderMenuOptionDetail> orderMenuOptionDetails) {
        this.name = name;
        this.orderMenuOptionDetails = orderMenuOptionDetails;
    }

    public int calculateMoney(){
        return orderMenuOptionDetails.stream().mapToInt(OrderMenuOptionDetail::calculateMoney).sum();
    }

    public OrderGetResDTO.OrderGetMenuOptionGroup toOrderGetMenuOptionGroup() {
        return new OrderGetResDTO.OrderGetMenuOptionGroup(
                name,
                orderMenuOptionDetails.stream().map(OrderMenuOptionDetail::toOrderGetMenuOptionGroupDetail).toList()
        );
    }
}

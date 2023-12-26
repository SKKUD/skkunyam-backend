package com.skkudteam3.skkusirenorder.src.entity;

import com.skkudteam3.skkusirenorder.src.dto.OrderGetResDTO;
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

    private String name;
    private int price;
    private int count;

    public OrderMenuOptionDetail(String name, int price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public int calculateMoney(){
        return price*count;
    }

    public OrderGetResDTO.OrderGetMenuOptionGroupDetail toOrderGetMenuOptionGroupDetail() {
        return new OrderGetResDTO.OrderGetMenuOptionGroupDetail(
                name,
                count
        );
    }
}

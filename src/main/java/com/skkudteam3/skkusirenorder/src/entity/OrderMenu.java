package com.skkudteam3.skkusirenorder.src.entity;

import com.skkudteam3.skkusirenorder.common.exceptions.OrderMenuCountLackException;
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
public class OrderMenu {

    @Id
    @Column(name = "order_menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private int count; // 주문수량

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_menu_id")
    private List<OrderMenuOptionGroup> orderMenuOptionGroups = new ArrayList<>();

    public OrderMenu(Menu menu, int count, List<OrderMenuOptionGroup> orderMenuOptionGroups) {
        this.menu = menu;
        this.count = count;
        this.orderMenuOptionGroups = orderMenuOptionGroups;
    }

    public int calculateMoney(){
        int detailPrice = orderMenuOptionGroups.stream().mapToInt(OrderMenuOptionGroup::calculateMoney).sum();
        int totalPrice = (detailPrice+menu.getMenuPrice())*count;
        return totalPrice;
    }

    // OrderMenu 검증
    public void validate(){
        if(count<1){
            throw new OrderMenuCountLackException();
        }
    }

    public OrderGetResDTO.OrderGetMenuItem toOrderGetMenuItem() {
        return new OrderGetResDTO.OrderGetMenuItem(
                menu.getMenuName(),
                count,
                orderMenuOptionGroups.stream().map(OrderMenuOptionGroup::toOrderGetMenuOptionGroup).toList()
        );
    }
}

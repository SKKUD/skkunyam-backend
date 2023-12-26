package com.skkudteam3.skkusirenorder.src.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
/*
    주문 1단계에서 사용하는 response DTO
 */
@Data
@NoArgsConstructor
public class OrderPostResDTO {

    private Long orderId;
    private int totalPrice;
    private String orderNumber; // 주문번호, = merchandise_uid

    public OrderPostResDTO(Long orderId, int totalPrice, String orderNumber) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.orderNumber = orderNumber;
    }
}

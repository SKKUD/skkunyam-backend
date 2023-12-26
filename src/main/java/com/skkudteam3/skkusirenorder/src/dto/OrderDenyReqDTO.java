package com.skkudteam3.skkusirenorder.src.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDenyReqDTO {
    private Long orderId;
    private String orderCancellationMessage;

    public OrderDenyReqDTO(Long orderId, String orderCancellationMessage) {
        this.orderId = orderId;
        this.orderCancellationMessage = orderCancellationMessage;
    }
}

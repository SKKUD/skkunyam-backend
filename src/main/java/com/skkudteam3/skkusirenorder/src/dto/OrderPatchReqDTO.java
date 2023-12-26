package com.skkudteam3.skkusirenorder.src.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderPatchReqDTO {
    public int estimatedTime;
    public Long orderId;

    public OrderPatchReqDTO(int estimatedTime, Long orderId) {
        this.estimatedTime = estimatedTime;
        this.orderId = orderId;
    }
}

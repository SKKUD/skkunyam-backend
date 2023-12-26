package com.skkudteam3.skkusirenorder.src.dto;

import com.skkudteam3.skkusirenorder.src.entity.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderGetResDTO {
    private String orderNumber;
    private OrderStatus orderStatus;
    private int totalPrice;
    private LocalDateTime createdAt;
    private boolean isTakeOut;
    private String requestMessage;
    private int estimatedTime;

    private Long customerId;
    private String customerName;

    private List<OrderGetMenuItem> orderGetMenuItems = new ArrayList<>();

    public OrderGetResDTO(String orderNumber, OrderStatus orderStatus, int totalPrice, LocalDateTime createdAt, boolean isTakeOut, String requestMessage, int estimatedTime, Long customerId, String customerName, List<OrderGetMenuItem> orderGetMenuItems) {
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.isTakeOut = isTakeOut;
        this.requestMessage = requestMessage;
        this.estimatedTime = estimatedTime;
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderGetMenuItems = orderGetMenuItems;
    }

    @Data
    @NoArgsConstructor
    public static class OrderGetMenuItem{
        private String name;
        private int count; // 해당 메뉴 주문량
        private List<OrderGetMenuOptionGroup> orderGetMenuOptionGroups = new ArrayList<>();

        public OrderGetMenuItem(String name, int count, List<OrderGetMenuOptionGroup> orderGetMenuOptionGroups) {
            this.name = name;
            this.count = count;
            this.orderGetMenuOptionGroups = orderGetMenuOptionGroups;
        }
    }

    @Data
    @NoArgsConstructor
    public static class OrderGetMenuOptionGroup {
        private String name;
        private List<OrderGetMenuOptionGroupDetail> orderGetMenuOptionGroupDetails = new ArrayList<>();

        public OrderGetMenuOptionGroup(String name, List<OrderGetMenuOptionGroupDetail> orderGetMenuOptionGroupDetails) {
            this.name = name;
            this.orderGetMenuOptionGroupDetails = orderGetMenuOptionGroupDetails;
        }
    }

    @Data
    @NoArgsConstructor
    public static class OrderGetMenuOptionGroupDetail {
        private String name;
        private int count; // 해당 옵션 선택량

        public OrderGetMenuOptionGroupDetail(String name, int count) {
            this.name = name;
            this.count = count;
        }
    }

    @Override
    public String toString() {
        return "OrderGetResDTO{" +
                "orderNumber='" + orderNumber + '\'' +
                ", orderStatus=" + orderStatus +
                ", totalPrice=" + totalPrice +
                ", createdAt=" + createdAt +
                ", isTakeOut=" + isTakeOut +
                ", requestMessage='" + requestMessage + '\'' +
                ", estimatedTime=" + estimatedTime +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", orderGetMenuItems=" + orderGetMenuItems +
                '}';
    }
}

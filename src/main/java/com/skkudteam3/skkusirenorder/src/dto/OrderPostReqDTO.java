package com.skkudteam3.skkusirenorder.src.dto;

import com.skkudteam3.skkusirenorder.src.entity.OrderMenu;
import com.skkudteam3.skkusirenorder.src.entity.OrderMenuOptionDetail;
import com.skkudteam3.skkusirenorder.src.entity.OrderMenuOptionGroup;
import com.skkudteam3.skkusirenorder.src.entity.Menu;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
/*
    주문 등록 시 사용하는 DTO
 */
@Data
@NoArgsConstructor
public class OrderPostReqDTO {

    private Long customerId; // 주문하는 소비자의 ID
    private String customerName; // 소비자 이름
    private Long cafeteriaId; // 주문받는 식당 ID
    private String requestMessage; // 주문 시 요청 메세지
    private Boolean isTakeOut; // 포장여부
    private List<CartMenuItem> cartMenuItems = new ArrayList<>(); // 장바구니에 담긴 "Menu"

    public OrderPostReqDTO(Long customerId, String customerName, Long cafeteriaId, String requestMessage, Boolean isTakeOut, List<CartMenuItem> cartMenuItems) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.cafeteriaId = cafeteriaId;
        this.requestMessage = requestMessage;
        this.isTakeOut = isTakeOut;
        this.cartMenuItems = cartMenuItems;
    }

    @Data
    @NoArgsConstructor
    public static class CartMenuItem{
        private Long menuId;
        private int count; // 해당 메뉴 주문량
        private List<CartOptionGroup> cartOptionGroups = new ArrayList<>();

        public CartMenuItem(Long menuId, String name, int count, List<CartOptionGroup> cartOptionGroups) {
            this.menuId = menuId;
            this.cartOptionGroups = cartOptionGroups;
        }

        public OrderMenu toEntity(Menu menu){
            List<OrderMenuOptionGroup> orderMenuOptionGroups = cartOptionGroups.stream().map(CartOptionGroup::toEntity).toList();
            return new OrderMenu(menu, count, orderMenuOptionGroups);
        }
    }

    @Data
    @NoArgsConstructor
    public static class CartOptionGroup {
        private String cartOptionGroupName;
        private List<CartMenuOptionDetail> cartOptions = new ArrayList<>();

        public CartOptionGroup(String cartOptionGroupName, List<CartMenuOptionDetail> cartOptions) {
            this.cartOptionGroupName = cartOptionGroupName;
            this.cartOptions = cartOptions;
        }

        public CartOptionGroup(String cartOptionGroupName) {
            this.cartOptionGroupName = cartOptionGroupName;
        }

        public OrderMenuOptionGroup toEntity() {
            List<OrderMenuOptionDetail> orderMenuOptionDetails = cartOptions.stream().map(CartMenuOptionDetail::toEntity).toList();
            return new OrderMenuOptionGroup(cartOptionGroupName, orderMenuOptionDetails);
        }
    }

    @Data
    @NoArgsConstructor
    public static class CartMenuOptionDetail {
        private String cartMenuOptionDetailName;
        private int cartOptionDetailPrice;
        private int cartOptionDetailCount; // 해당 옵션 선택량

        public CartMenuOptionDetail(String cartMenuOptionDetailName, int cartOptionDetailPrice, int cartOptionDetailCount) {
            this.cartMenuOptionDetailName = cartMenuOptionDetailName;
            this.cartOptionDetailPrice = cartOptionDetailPrice;
            this.cartOptionDetailCount = cartOptionDetailCount;
        }

        public OrderMenuOptionDetail toEntity(){
            return new OrderMenuOptionDetail(cartMenuOptionDetailName, cartOptionDetailPrice, cartOptionDetailCount);
        }
    }

}

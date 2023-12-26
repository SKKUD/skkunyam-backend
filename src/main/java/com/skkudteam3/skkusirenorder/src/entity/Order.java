package com.skkudteam3.skkusirenorder.src.entity;

import com.skkudteam3.skkusirenorder.src.dto.OrderGetResDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private int totalPrice;
    private String orderNumber;
    private LocalDateTime createdAt;
    private Boolean isTakeOut;
    private String requestMessage; // 주문 요청 메세지
    private String orderCancellationMessage; // 주문 취소 사유
    private int estimatedTime; // 해당 주문 예상 준비 시간

    private Long customerId; // 주문자 ~ order입장에서 유저를 조회를 할 일이 없음...!!
    private String customerName; // 주문자명

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafeteria_id")
    private Cafeteria cafeteria;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderMenu> orderMenus = new ArrayList<>();


    // 주문 생성
    public Order(Long customerId, String customerName, Boolean isTakeOut, String requestMessage, Cafeteria cafeteria, List<OrderMenu> orderMenus){
        this.customerId = customerId;
        this.customerName = customerName;
        this.isTakeOut = isTakeOut;
        this.requestMessage = requestMessage;
        this.cafeteria = cafeteria;
        this.orderMenus = orderMenus;
        this.createdAt = LocalDateTime.now();
    }

    private void changeOrderStatus(OrderStatus orderStatus){
        this.orderStatus=orderStatus;
    }

    // 주문 검증의 시작 위치
    public void place(){
        validate();
        ordered();
    }

    private void validate(){

        // 가게 존재여부 확인
        if (cafeteria == null){
            throw new IllegalStateException("해당 가게는 존재하지 않습니다.");
        }

        // 주문 항목 확인
        if (orderMenus.isEmpty()) {
            throw new IllegalStateException("주문 항목이 비어 있습니다.");
        }

        // 영업여부 확인
        if (!cafeteria.isOpen()) {
            throw new IllegalStateException("가게가 영업중이 아닙니다.");
        }

        // TODO : 주문항목 검증
        for (OrderMenu orderMenu : orderMenus) {
            orderMenu.validate();
        }
    }
    public void ordered(){ // 주문번호 생성 후 PRE_WAITING 상태로 변경.
        if (this.orderStatus != null){
            throw new IllegalStateException("해당 주문은 만료된 주문입니다.");
        }
        this.orderNumber = makeOrderNumber();
        changeOrderStatus(OrderStatus.PRE_WAITING);
    }

    private String makeOrderNumber(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyymmddhhmmss");
        String date = this.createdAt.format(formatter);
        String id = "skkunyam"+customerId;
        return date+id;
    }

    public void payed(){
        if (this.orderStatus != OrderStatus.PRE_WAITING){
            throw new IllegalStateException("주문 상태가 PRE_WAITING이어야합니다.");
        }
        changeOrderStatus(OrderStatus.WAITING);
    }

    public void accepted(int estimatedTime){
        if (this.orderStatus != OrderStatus.WAITING){
            throw new IllegalStateException("주문 상태가 WAITING이어야합니다.");
        }
        this.estimatedTime = estimatedTime;
        changeOrderStatus(OrderStatus.PROCEEDING);
    }

    public void completed(){
        if (this.orderStatus != OrderStatus.PROCEEDING){
            throw new IllegalStateException("주문 상태가 PROCEEDING이어야합니다.");
        }
        changeOrderStatus(OrderStatus.COMPLETE);
    }

    public void denied(String orderCancellationMessage){
        if (this.orderStatus != OrderStatus.WAITING){
            throw new IllegalStateException("주문 상태가 WAITING이어야합니다.");
        }
        this.orderCancellationMessage = orderCancellationMessage; // 취소사유
        changeOrderStatus(OrderStatus.CANCEL);
    }

    public int calculateTotalPrice(){
        this.totalPrice = orderMenus.stream().mapToInt(OrderMenu::calculateMoney).sum();
        return totalPrice;
    }


    /*
    DTO 변환
     */

    public OrderGetResDTO toOrderGetResDTO(){
        return new OrderGetResDTO(
                orderNumber,
                orderStatus,
                totalPrice,
                createdAt,
                isTakeOut,
                requestMessage,
                estimatedTime,
                customerId,
                customerName,
                orderMenus.stream().map(OrderMenu::toOrderGetMenuItem).toList()
                );
    }

}

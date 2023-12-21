package com.skkudteam3.skkusirenorder.src.service;

import com.skkudteam3.skkusirenorder.src.dto.*;
import com.skkudteam3.skkusirenorder.src.entity.*;
import com.skkudteam3.skkusirenorder.src.repository.CafeteriaRepository;
import com.skkudteam3.skkusirenorder.src.repository.MenuRepository;
import com.skkudteam3.skkusirenorder.src.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final CafeteriaRepository cafeteriaRepository;
    private final MenuRepository menuRepository;

    private Order toEntity(OrderPostReqDTO orderPostReqDTO) {
        Cafeteria cafeteria = cafeteriaRepository.findById(orderPostReqDTO.getCafeteriaId()).orElseThrow(IllegalArgumentException::new);

        List<OrderMenu> orderMenus = new ArrayList<>();
        for(OrderPostReqDTO.CartMenuItem cartMenuItem : orderPostReqDTO.getCartMenuItems()){
            Menu menu = menuRepository.findById(cartMenuItem.getMenuId()).orElseThrow(IllegalArgumentException::new);
            orderMenus.add(cartMenuItem.toEntity(menu));
        }

        return new Order(orderPostReqDTO.getCustomerId(),
                orderPostReqDTO.getCustomerName(),
                orderPostReqDTO.getIsTakeOut(),
                orderPostReqDTO.getRequestMessage(),
                cafeteria,
                orderMenus
        );
    }

    /*
        주문 1단계 (PRE-WAITING)
     */
    @Transactional
    public OrderPostResDTO placeOrder(OrderPostReqDTO orderPostReqDTO) {
        Order order = toEntity(orderPostReqDTO); // 최초 주문 객체 생성
        order.place(); // 주문하기
        orderRepository.save(order);

        int total_price = order.calculateTotalPrice();
        String orderNumber = order.getOrderNumber();

        return new OrderPostResDTO(order.getId(), total_price, orderNumber); // 성공시 주문ID + 주문번호 + 결제 금액 반환하기
    }
    /*
        주문 2단계 (WAITING)
     */
    @Transactional
    public void payOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
        order.payed();
    }

    /*
        상태별 주문 확인 PRE_WAITING, WAITING, PROCEEDING, COMPLETE, CANCEL
     */
    public List<OrderGetResDTO> findOrdersByStatus(OrderStatus orderStatus){
        List<Order> orders = orderRepository.findAllOrderByOrderStatus(orderStatus);
        return orders.stream().map(Order::toOrderGetResDTO).toList();
    }

    /*
        주문 3단계 (PROCEEDING)
        - 주문 상태 변경
        - 예상대기시간 추가
     */
    @Transactional
    public void acceptOrder(OrderPatchReqDTO orderPatchReqDTO) {
        Long orderId = orderPatchReqDTO.orderId;
        int estimatedTime = orderPatchReqDTO.estimatedTime;

        Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
        order.accepted(estimatedTime);
    }

    /*
        주문 4단계 (COMPLETE)
        - 주문 상태 변경
     */
    @Transactional
    public void completeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
        order.completed();

    }

    /*
        주문 거절 단계 (CANCEL)
        - 주문 상태 변경
        - 주문 취소 사유 메세지 전달
     */
    @Transactional
    public void denyOrder(OrderDenyReqDTO orderDenyReqDTO) {
        Order order = orderRepository.findById(orderDenyReqDTO.getOrderId()).orElseThrow(IllegalArgumentException::new);
        order.denied(orderDenyReqDTO.getOrderCancellationMessage());
    }

    /*
        거래 미승인 단계 (CANCEL)
        - 주문 상태 변경
     */
    @Transactional
    public void denyTransaction(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
        order.denied("미승인된 거래입니다.");
    }

}

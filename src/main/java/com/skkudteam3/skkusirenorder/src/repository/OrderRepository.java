package com.skkudteam3.skkusirenorder.src.repository;

import com.skkudteam3.skkusirenorder.src.entity.Order;
import com.skkudteam3.skkusirenorder.src.entity.OrderStatus;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public Optional<Order> findById(Long id){
        return Optional.ofNullable(em.find(Order.class, id));
    }

    public void save(Order order){
        em.persist(order); // cascade_all
    }

    public List<Order> findAllOrderByOrderStatus(OrderStatus orderStatus){
        return em.createQuery("select o " +
                        "from Order o " +
                        "where o.orderStatus=:orderStatus", Order.class)
                .setParameter("orderStatus", orderStatus) // 바인딩
                .getResultList();
    }

}

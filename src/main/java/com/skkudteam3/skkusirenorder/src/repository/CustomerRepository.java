package com.skkudteam3.skkusirenorder.src.repository;

import com.skkudteam3.skkusirenorder.src.entity.Customer;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {
    private final EntityManager em;

    public void saveCustomer(Customer customer){
        em.persist(customer);
    }

    public Customer findOne(Long id){
        return em.find(Customer.class, id);
    }

    public List<Customer> findAll(){
        return em.createQuery("select m from Customer m")
            .getResultList();
    }

    public List<Customer> findByName(String username) {
        return em.createQuery("select m from Customer m where m.username = :username", Customer.class)
        .setParameter("username", username)
        .getResultList();
    }
}

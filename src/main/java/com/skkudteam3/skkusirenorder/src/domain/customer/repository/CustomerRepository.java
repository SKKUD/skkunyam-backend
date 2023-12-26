package com.skkudteam3.skkusirenorder.src.domain.customer.repository;

import com.skkudteam3.skkusirenorder.src.domain.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

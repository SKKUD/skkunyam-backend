package com.skkudteam3.skkusirenorder.src.service;

import com.skkudteam3.skkusirenorder.src.entity.Customer;
import com.skkudteam3.skkusirenorder.src.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public Long join(Customer customer){
        validateDuplicateMember(customer);
        customerRepository.saveCustomer(customer);
        return customer.getId();
    }

    private void validateDuplicateMember(Customer customer) {
        List<Customer> findMembers = customerRepository.findByName(customer.getCustomerName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        }
    }

    public List<Customer> findMembers() {
        return customerRepository.findAll();
    }

    public Customer findOne(Long customerId) {
        return customerRepository.findOne(customerId);
    }
}

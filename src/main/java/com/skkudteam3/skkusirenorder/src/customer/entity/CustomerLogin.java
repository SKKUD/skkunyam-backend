package com.skkudteam3.skkusirenorder.src.customer.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // Entity에서는 Setter를 만들면 안됨!!!! => 별도의 메서드를 만들기!!
@Entity // 필수, Class 를 Database Table화 해주는 것이다
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CUSTOMER_LOGIN")
public class CustomerLogin {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_login_id")
    private Long id;
    private String email;

    @OneToOne(mappedBy = "customerLogin")
    private Customer customer;

}

package com.skkudteam3.skkusirenorder.src.customer.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CUSTOMER_IMAGE")
public class CustomerImage {
    @Id
    @Column(name = "customer_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerImagePath;

    @OneToOne(mappedBy = "customerImage")
    private Customer customer;

    public void changeImage(String path){
        this.customerImagePath = path;
    }
}

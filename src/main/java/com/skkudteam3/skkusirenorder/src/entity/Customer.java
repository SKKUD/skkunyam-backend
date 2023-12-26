<<<<<<< HEAD:src/main/java/com/skkudteam3/skkusirenorder/src/domain/customer/entity/Customer.java
package com.skkudteam3.skkusirenorder.src.domain.customer.entity;
=======
package com.skkudteam3.skkusirenorder.src.entity;
>>>>>>> ad0a4e72ed59ac251a7e0ce70488b54343b1622d:src/main/java/com/skkudteam3/skkusirenorder/src/entity/Customer.java

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
<<<<<<< HEAD:src/main/java/com/skkudteam3/skkusirenorder/src/domain/customer/entity/Customer.java
import org.hibernate.annotations.DynamicUpdate;
=======
>>>>>>> ad0a4e72ed59ac251a7e0ce70488b54343b1622d:src/main/java/com/skkudteam3/skkusirenorder/src/entity/Customer.java

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)//기본 생성자 생성
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String email;
    private int phoneNumber;
    private SocialType socialType;
    private int point;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    @OneToOne
    @JoinColumn(name = "customer_login_id")
    private CustomerLogin customerLogin;

    @OneToOne
    @JoinColumn(name = "customer_image_id")
    private CustomerImage customerImage;

    @OneToMany(mappedBy = "customer")
    private List<Bookmark> bookmarks = new ArrayList<>();

    private Customer(String customerName, String email, int phoneNumber,SocialType socialType, int point){
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.socialType = socialType;
        this.point = point;
    }

    //정적 팩토리 메소드 -> 무분별한 class 생성 방지
    public static Customer createCustomer(String customerName, String email, int phoneNumber,SocialType socialType, int point){
        return new Customer(customerName, email, phoneNumber, socialType, point);
    }

    // point 수정 시 사용
    public void changePoint (int point){
        this.point = point;
    }

    // 사용자 정보 수정
    public void updateProfile (String customerName, int phoneNumber) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
    }

}

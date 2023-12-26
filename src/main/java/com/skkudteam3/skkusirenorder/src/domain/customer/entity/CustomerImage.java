<<<<<<< HEAD:src/main/java/com/skkudteam3/skkusirenorder/src/domain/customer/entity/CustomerImage.java
package com.skkudteam3.skkusirenorder.src.domain.customer.entity;
=======
package com.skkudteam3.skkusirenorder.src.entity;
>>>>>>> ad0a4e72ed59ac251a7e0ce70488b54343b1622d:src/main/java/com/skkudteam3/skkusirenorder/src/entity/CustomerImage.java

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

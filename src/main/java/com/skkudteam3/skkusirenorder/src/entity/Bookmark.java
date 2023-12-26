<<<<<<< HEAD:src/main/java/com/skkudteam3/skkusirenorder/src/domain/customer/entity/Bookmark.java
package com.skkudteam3.skkusirenorder.src.domain.customer.entity;
=======
package com.skkudteam3.skkusirenorder.src.entity;
>>>>>>> ad0a4e72ed59ac251a7e0ce70488b54343b1622d:src/main/java/com/skkudteam3/skkusirenorder/src/entity/Bookmark.java

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CUSTOMER_IMAGE")
public class Bookmark {
    @Id
    @Column(name = "bookmark_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    // 식당 추가

}

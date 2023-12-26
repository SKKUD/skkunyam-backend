<<<<<<< HEAD:src/main/java/com/skkudteam3/skkusirenorder/src/domain/admin/entity/AdminLogin.java
package com.skkudteam3.skkusirenorder.src.domain.admin.entity;


import com.skkudteam3.skkusirenorder.src.domain.boards.entity.Board;
=======
package com.skkudteam3.skkusirenorder.src.entity;


>>>>>>> ad0a4e72ed59ac251a7e0ce70488b54343b1622d:src/main/java/com/skkudteam3/skkusirenorder/src/entity/AdminLogin.java
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ADMIN_LOGIN")
public class AdminLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_login_id")
    private Long id;
    private String email;

    @OneToOne(mappedBy = "adminLogin")
    private Board board;
}

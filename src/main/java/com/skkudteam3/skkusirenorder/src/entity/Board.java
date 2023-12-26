<<<<<<< HEAD:src/main/java/com/skkudteam3/skkusirenorder/src/domain/boards/entity/Board.java
package com.skkudteam3.skkusirenorder.src.domain.boards.entity;

import com.skkudteam3.skkusirenorder.src.domain.admin.entity.AdminLogin;
=======
package com.skkudteam3.skkusirenorder.src.entity;

>>>>>>> ad0a4e72ed59ac251a7e0ce70488b54343b1622d:src/main/java/com/skkudteam3/skkusirenorder/src/entity/Board.java
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "BOARD")
public class Board {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    @OneToOne
    @JoinColumn(name = "admin_login_id")
    private AdminLogin adminLogin;

    @OneToMany(mappedBy = "board")
    private List<File> files = new ArrayList<>();



}

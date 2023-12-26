<<<<<<< HEAD:src/main/java/com/skkudteam3/skkusirenorder/src/domain/boards/entity/File.java
package com.skkudteam3.skkusirenorder.src.domain.boards.entity;
=======
package com.skkudteam3.skkusirenorder.src.entity;
>>>>>>> ad0a4e72ed59ac251a7e0ce70488b54343b1622d:src/main/java/com/skkudteam3/skkusirenorder/src/entity/File.java

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "FILE")
public class File {
    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String file_name;
    private String file_path;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

}

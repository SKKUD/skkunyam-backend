package com.skkudteam3.skkusirenorder.src.cafeteria.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CafeteriaImage {

    @Id
    @Column(name = "cafeteria_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cafeteriaImagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafeteria_id")
    private Cafeteria cafeteria;

}

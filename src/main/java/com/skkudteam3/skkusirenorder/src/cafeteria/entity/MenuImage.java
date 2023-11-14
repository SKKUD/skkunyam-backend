package com.skkudteam3.skkusirenorder.src.cafeteria.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuImage {

    @Id
    @Column(name = "menu_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String menuImageUrl;


}

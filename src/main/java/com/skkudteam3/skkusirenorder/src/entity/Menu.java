package com.skkudteam3.skkusirenorder.src.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String menuName;
    private int menuPrice;
    private String menuCategory;
    private Boolean isBest;
    private Boolean isSeason;
    private Boolean isSoldOut;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "menu_id")
    private List<MenuOptionGroup> menuOptionGroups = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "menu_image_id")
    private MenuImage menuImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafeteria_id")
    private Cafeteria cafeteria;

    public void soldOut(){this.isSoldOut=true;}
    public void notSoldOut(){this.isSoldOut=false;}

    public void adoptBestMenu(){this.isBest=true;}
    public void dismissBestMenu(){this.isBest=false;}

    public void adoptSeasonMenu(){this.isSeason=true;}
    public void dismissSeasonMenu(){this.isSeason=false;}

    public void setCafeteria(Cafeteria cafeteria){
        this.cafeteria = cafeteria;
    }

    public void setMenuImage(MenuImage menuImage){
        this.menuImage = menuImage;
    }

    public Menu(String menuName, int menuPrice, String menuCategory, Boolean isSeason, List<MenuOptionGroup> menuOptionGroups) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.isSeason = isSeason;
        this.menuCategory = menuCategory;
        this.menuOptionGroups = menuOptionGroups;
    }
}

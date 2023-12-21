package com.skkudteam3.skkusirenorder.src.entity;

import com.skkudteam3.skkusirenorder.src.dto.CafeteriaPatchReqDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cafeteria {

    @Id
    @Column(name = "cafetria_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String description;
    private String name;
    private String location; // 그냥 학생회관 0000 위치 정도로 작성.

    private Boolean isCafeteriaOpen;
    private LocalDateTime closeTime;
    private LocalDateTime openTime;
    @Embedded
    private WeekDays weekDays;

    @Enumerated(EnumType.STRING)
    private Campus campus;


    private String mapImagePath; // 약도 이미지 경로
    private String contact; // 가게 연락처 (전화번호)
    private String email;

    @OneToMany(mappedBy = "cafeteria")
    private List<CafeteriaImage> cafeteriaImages = new ArrayList<>();

    @OneToMany(mappedBy = "cafeteria")
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(mappedBy = "cafeteria")
    private List<Order> orders = new ArrayList<>();

    /*
    DDD
     */
    public Boolean isOpen() {
        return this.isCafeteriaOpen;
    } // 가게 개장 상태 확인

    public void open() { // 가게 개장 상태 변경.
        this.isCafeteriaOpen = true;
    } // 가게 오픈

    public void close() {
        this.isCafeteriaOpen = false;
    } // 가게 닫음

    /*
    생성자
     */
    public Cafeteria(String description, String name, String location, LocalDateTime closeTime, LocalDateTime openTime, WeekDays weekDays, Campus campus, String contact, String email) {
        this.description = description;
        this.name = name;
        this.location = location;
        this.closeTime = closeTime;
        this.openTime = openTime;
        this.weekDays = weekDays;
        this.campus = campus;
        this.contact = contact;
        this.email = email;
        this.isCafeteriaOpen = false;
    }

    /*
    편의 메소드
     */
    public void updateInfo(CafeteriaPatchReqDTO cafeteriaPatchReqDTO){
        this.name = cafeteriaPatchReqDTO.getName();
        this.location = cafeteriaPatchReqDTO.getLocation();
        this.description = cafeteriaPatchReqDTO.getDescription();
        this.contact = cafeteriaPatchReqDTO.getContact();
        this.closeTime = cafeteriaPatchReqDTO.getCloseTime();
        this.openTime = cafeteriaPatchReqDTO.getOpenTime();
        this.weekDays = cafeteriaPatchReqDTO.getWeekDays();
    }

    public void addMenu(Menu menu) {
        menus.add(menu);
        menu.setCafeteria(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cafeteria cafeteria = (Cafeteria) o;
        return Objects.equals(id, cafeteria.id) && Objects.equals(description, cafeteria.description) && Objects.equals(name, cafeteria.name) && Objects.equals(location, cafeteria.location) && Objects.equals(isCafeteriaOpen, cafeteria.isCafeteriaOpen) && Objects.equals(closeTime, cafeteria.closeTime) && Objects.equals(openTime, cafeteria.openTime) && Objects.equals(weekDays, cafeteria.weekDays) && campus == cafeteria.campus && Objects.equals(mapImagePath, cafeteria.mapImagePath) && Objects.equals(contact, cafeteria.contact) && Objects.equals(email, cafeteria.email) && Objects.equals(cafeteriaImages, cafeteria.cafeteriaImages) && Objects.equals(menus, cafeteria.menus) && Objects.equals(orders, cafeteria.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, name, location, isCafeteriaOpen, closeTime, openTime, weekDays, campus, mapImagePath, contact, email, cafeteriaImages, menus, orders);
    }
}

package com.skkudteam3.skkusirenorder.src.repository;

import com.skkudteam3.skkusirenorder.src.entity.MenuOptionDetail;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MenuOptionDetailRepository {
    private final EntityManager em;

    public Optional<MenuOptionDetail> findById(Long id){
        return Optional.ofNullable(em.find(MenuOptionDetail.class, id));
    }
}

package com.skkudteam3.skkusirenorder.src.repository;

import com.skkudteam3.skkusirenorder.src.entity.Menu;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class MenuRepository {
    private final EntityManager em;

    public Optional<Menu> findById(Long id){
        return Optional.ofNullable(em.find(Menu.class, id));
    }
    public Long save(Menu menu){
        em.persist(menu);
        return menu.getId();
    }
}

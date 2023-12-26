package com.skkudteam3.skkusirenorder.src.repository;

import com.skkudteam3.skkusirenorder.src.entity.MenuOptionGroup;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MenuOptionGroupRepository {
    private final EntityManager em;

    public Optional<MenuOptionGroup> findById(Long id){
        return Optional.ofNullable(em.find(MenuOptionGroup.class, id));
    }


}

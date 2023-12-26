package com.skkudteam3.skkusirenorder.src.repository;

import com.skkudteam3.skkusirenorder.src.entity.Cafeteria;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class CafeteriaRepository {
    private final EntityManager em;

    public Optional<Cafeteria> findById(Long id){
        return Optional.ofNullable(em.find(Cafeteria.class, id));
    }
    public Long save(Cafeteria cafeteria) {
        em.persist(cafeteria);
        return cafeteria.getId();
    }


}

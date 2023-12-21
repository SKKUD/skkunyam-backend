package com.skkudteam3.skkusirenorder.src.service;

import com.skkudteam3.skkusirenorder.common.exceptions.CafeteriaNotFoundException;
import com.skkudteam3.skkusirenorder.src.dto.CafeteriaPatchReqDTO;
import com.skkudteam3.skkusirenorder.src.dto.CafeteriaPostReqDTO;
import com.skkudteam3.skkusirenorder.src.entity.Cafeteria;
import com.skkudteam3.skkusirenorder.src.repository.CafeteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CafeteriaService {

    private final CafeteriaRepository cafeteriaRepository;

    public Cafeteria findCafeteria(Long id){
        return cafeteriaRepository.findById(id).orElseThrow(CafeteriaNotFoundException::new);
    }

    @Transactional
    public Long saveCafeteria(CafeteriaPostReqDTO cafeteriaPostReqDTO){

        return cafeteriaRepository.save(cafeteriaPostReqDTO.toEntity());
    }

    @Transactional
    public Boolean openCafeteria(Long id){

        Cafeteria cafeteria = findCafeteria(id);
        cafeteria.open();

        return cafeteria.getIsCafeteriaOpen();
    }

    @Transactional
    public Boolean closeCafeteria(Long id){

        Cafeteria cafeteria = findCafeteria(id);
        cafeteria.close();

        return cafeteria.getIsCafeteriaOpen();
    }

    @Transactional
    public void updateCafeteria(CafeteriaPatchReqDTO cafeteriaPatchReqDTO){
        Cafeteria cafeteria = findCafeteria(cafeteriaPatchReqDTO.getCafeteriaId());
        cafeteria.updateInfo(cafeteriaPatchReqDTO);
    }
}

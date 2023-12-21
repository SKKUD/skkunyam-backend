package com.skkudteam3.skkusirenorder.src.service;

import com.skkudteam3.skkusirenorder.src.entity.Cafeteria;
import com.skkudteam3.skkusirenorder.src.entity.Menu;
import com.skkudteam3.skkusirenorder.src.dto.MenuPostReqDTO;
import com.skkudteam3.skkusirenorder.src.repository.CafeteriaRepository;
import com.skkudteam3.skkusirenorder.src.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;
    private final CafeteriaRepository cafeteriaRepository;

    /*
    메뉴 추가 (menu 변환 -> menu 저장 -> 해당 메뉴 cafeteria에 추가)
     */

    @Transactional
    public Long saveMenu(MenuPostReqDTO menuPostReqDTO){
        Cafeteria cafeteria = cafeteriaRepository.findById(menuPostReqDTO.getCafeteriaId()).orElseThrow(IllegalArgumentException::new);
        Menu menu = menuPostReqDTO.toEntity();
        menu.setCafeteria(cafeteria);
        Long menuId = menuRepository.save(menu);

        cafeteria.addMenu(menu);

        return menuId;
    }

    /*
    메뉴 수정
     */


}

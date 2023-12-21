package com.skkudteam3.skkusirenorder.src.service.menuservice;

import com.skkudteam3.skkusirenorder.src.dto.CafeteriaPostReqDTO;
import com.skkudteam3.skkusirenorder.src.dto.MenuPostReqDTO;
import com.skkudteam3.skkusirenorder.src.entity.Cafeteria;
import com.skkudteam3.skkusirenorder.src.entity.Campus;
import com.skkudteam3.skkusirenorder.src.entity.Menu;
import com.skkudteam3.skkusirenorder.src.entity.WeekDays;
import com.skkudteam3.skkusirenorder.src.repository.CafeteriaRepository;
import com.skkudteam3.skkusirenorder.src.repository.MenuRepository;
import com.skkudteam3.skkusirenorder.src.service.CafeteriaService;
import com.skkudteam3.skkusirenorder.src.service.MenuService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MenuServiceTest {

    @Autowired
    MenuService menuService;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    CafeteriaService cafeteriaService;
    @Before
    public void setUp() throws Exception{
        CafeteriaPostReqDTO cafeteriaPostReqDTO = new CafeteriaPostReqDTO();

        cafeteriaPostReqDTO.setCampus(Campus.NATURAL);
        cafeteriaPostReqDTO.setContact("01012345678");
        cafeteriaPostReqDTO.setEmail("test@test.com");

        cafeteriaPostReqDTO.setLocation("학생회관 1234");
        cafeteriaPostReqDTO.setDescription("여러분은 분위기 타세요 커피는 저희가 탈게요");
        cafeteriaPostReqDTO.setName("낭만카페");

        WeekDays weekDays = new WeekDays(true, true, true, true, true, false, false);
        cafeteriaPostReqDTO.setWeekDays(weekDays);
        cafeteriaPostReqDTO.setCloseTime(LocalDateTime.now());
        cafeteriaPostReqDTO.setOpenTime(LocalDateTime.now());

        cafeteriaService.saveCafeteria(cafeteriaPostReqDTO);
    }
    @Test
    public void menuSave_default() throws Exception{
        //given
        MenuPostReqDTO menuPostReqDTO = new MenuPostReqDTO();
        menuPostReqDTO.setIsSeason(false);
        menuPostReqDTO.setCafeteriaId(1L);
        menuPostReqDTO.setName("아메리카노");
        menuPostReqDTO.setPrice(1000);
        menuPostReqDTO.setCategory("COFFEE");

        List<MenuPostReqDTO.MenuOptionDetailDTO> menuOptionDetailDTOS1 = new ArrayList<>();
        menuOptionDetailDTOS1.add(new MenuPostReqDTO.MenuOptionDetailDTO("HOT", 500));
        menuOptionDetailDTOS1.add(new MenuPostReqDTO.MenuOptionDetailDTO("ICE", 500));

        List<MenuPostReqDTO.MenuOptionDetailDTO> menuOptionDetailDTOS2 = new ArrayList<>();
        menuOptionDetailDTOS2.add(new MenuPostReqDTO.MenuOptionDetailDTO("SHOT-1", 700));
        menuOptionDetailDTOS2.add(new MenuPostReqDTO.MenuOptionDetailDTO("SHOT-2", 1400));

        List<MenuPostReqDTO.MenuOptionGroupDTO> menuOptionGroupDTO = new ArrayList<>();
        menuOptionGroupDTO.add(new MenuPostReqDTO.MenuOptionGroupDTO("온도", true, menuOptionDetailDTOS1));
        menuOptionGroupDTO.add(new MenuPostReqDTO.MenuOptionGroupDTO("샷 추가", true, menuOptionDetailDTOS2));


        menuPostReqDTO.setMenuOptionGroupDTOS(menuOptionGroupDTO);

        //when
        Long findId = menuService.saveMenu(menuPostReqDTO);
        Menu menu = menuRepository.findById(findId).get();

        //then
        System.out.println(menu.getMenuName());
        menu.getMenuOptionGroups().forEach(menuOptionGroup -> {
            System.out.println("======" + menuOptionGroup.getName() + "======");
            menuOptionGroup.getMenuOptionDetails().forEach(
                    menuOptionDetail -> {
                        System.out.println(menuOptionDetail.getName());
                    }
            );

        });
    }


}
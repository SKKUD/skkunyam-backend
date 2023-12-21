package com.skkudteam3.skkusirenorder.src.controller.menucontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skkudteam3.skkusirenorder.src.dto.CafeteriaPostReqDTO;
import com.skkudteam3.skkusirenorder.src.dto.MenuPostReqDTO;
import com.skkudteam3.skkusirenorder.src.entity.Campus;
import com.skkudteam3.skkusirenorder.src.entity.WeekDays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void registerCafeteria() throws Exception{
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

        String param = objectMapper.writeValueAsString(cafeteriaPostReqDTO);

        this.mockMvc
                .perform(post("/cafeteria/")
                        .contentType(MediaType.APPLICATION_JSON).content(param))
                .andExpect(
                        status().isCreated()
                )
                .andDo(
                        print()
                );

    }

    @Test
    public void registerMenuTest_default() throws Exception{
        //given
        MenuPostReqDTO menuPostReqDTO = new MenuPostReqDTO();
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

        String param = objectMapper.writeValueAsString(menuPostReqDTO);

        //when //then
        this.mockMvc
                .perform(post("/menu/")
                        .contentType(MediaType.APPLICATION_JSON).content(param))
                .andExpect(
                        status().isCreated()
                )
                .andDo(
                        print()
                );

    }

}
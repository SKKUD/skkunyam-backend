package com.skkudteam3.skkusirenorder.src.controller.cafeteriacontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skkudteam3.skkusirenorder.src.dto.CafeteriaPostReqDTO;
import com.skkudteam3.skkusirenorder.src.entity.Cafeteria;
import com.skkudteam3.skkusirenorder.src.entity.Campus;
import com.skkudteam3.skkusirenorder.src.entity.WeekDays;
import com.skkudteam3.skkusirenorder.src.repository.CafeteriaRepository;
import com.skkudteam3.skkusirenorder.src.service.CafeteriaService;
import org.junit.Assert;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CafeteriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CafeteriaService cafeteriaService;

    @Autowired
    private CafeteriaRepository cafeteriaRepository;

    private static ObjectMapper objectMapper = new ObjectMapper();


    
    @Test
    public void registerCafeteriaTest_default() throws Exception{
        //given
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

        //when //then
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

    @Before
    public void setUp() throws Exception{
        //given
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

        //when //then
        this.mockMvc
                .perform(post("/cafeteria/")
                        .contentType(MediaType.APPLICATION_JSON).content(param))
                .andExpect(
                        status().isCreated()
                )
                .andDo(
                        print()
                );
        Cafeteria cafeteria = cafeteriaRepository.findById(1L).get();
        Assert.assertEquals(cafeteria, cafeteriaPostReqDTO.toEntity());
    }

    @Test
    public void openCafeteriaTest_default() throws Exception{

        //when //then
        this.mockMvc
                .perform(patch("/cafeteria/open/1"))
                .andExpect(
                        status().isAccepted()
                )
                .andDo(
                        print()
                );

        Assert.assertEquals(cafeteriaService.findCafeteria(1L).getIsCafeteriaOpen(), true);
    }

    @Test
    public void closeCafeteriaTest_default() throws Exception{

        //when //then
        this.mockMvc
                .perform(patch("/cafeteria/close/1"))
                .andExpect(
                        status().isAccepted()
                )
                .andDo(
                        print()
                );
        Assert.assertEquals(cafeteriaService.findCafeteria(1L).getIsCafeteriaOpen(), false);
    }

}
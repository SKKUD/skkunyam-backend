package com.skkudteam3.skkusirenorder.src.service.cafeteriaservice;

import com.skkudteam3.skkusirenorder.common.exceptions.CafeteriaNotFoundException;
import com.skkudteam3.skkusirenorder.src.dto.CafeteriaPostReqDTO;
import com.skkudteam3.skkusirenorder.src.entity.WeekDays;
import com.skkudteam3.skkusirenorder.src.entity.Cafeteria;
import com.skkudteam3.skkusirenorder.src.entity.Campus;
import com.skkudteam3.skkusirenorder.src.repository.CafeteriaRepository;
import com.skkudteam3.skkusirenorder.src.service.CafeteriaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CafeteriaServiceTest {

    @Autowired
    CafeteriaRepository cafeteriaRepository;

    @Autowired
    CafeteriaService cafeteriaService;

    @Test
    public void cafeteriaSave_default() throws Exception{
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

        //when
        Long cafeteriaId = cafeteriaService.saveCafeteria(cafeteriaPostReqDTO);
        Cafeteria cafeteria = cafeteriaRepository.findById(cafeteriaId).get();

        //then
        System.out.println(cafeteria.getContact());
        System.out.println(cafeteria.getEmail());
        System.out.println(cafeteria.getDescription());

        System.out.println(cafeteria.getLocation());
        System.out.println(cafeteria.getCampus());
        System.out.println(cafeteria.getCloseTime());

        System.out.println(cafeteria.getOpenTime());
        System.out.println(cafeteria.getName());
        System.out.println("화요일 : " + cafeteria.getWeekDays().getTue());
        System.out.println("일요일 : " + cafeteria.getWeekDays().getSun());
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

        //when
        Long cafeteriaId = cafeteriaService.saveCafeteria(cafeteriaPostReqDTO);
    }
    @Test(expected = CafeteriaNotFoundException.class)
    public void findCafeteria_not_found() throws Exception{
        //given
        //when
        cafeteriaService.findCafeteria(2L);

        //then
        fail("CafeteriaNotFoundException이 발생하지 않았습니다.");
    }
}
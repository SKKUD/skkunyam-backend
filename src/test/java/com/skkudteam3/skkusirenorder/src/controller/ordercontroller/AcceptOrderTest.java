package com.skkudteam3.skkusirenorder.src.controller.ordercontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skkudteam3.skkusirenorder.src.dto.CafeteriaPostReqDTO;
import com.skkudteam3.skkusirenorder.src.dto.MenuPostReqDTO;
import com.skkudteam3.skkusirenorder.src.dto.OrderPostReqDTO;
import com.skkudteam3.skkusirenorder.src.entity.*;
import com.skkudteam3.skkusirenorder.src.dto.OrderPatchReqDTO;
import com.skkudteam3.skkusirenorder.src.repository.OrderRepository;
import com.skkudteam3.skkusirenorder.src.service.CafeteriaService;
import com.skkudteam3.skkusirenorder.src.service.MenuService;
import com.skkudteam3.skkusirenorder.src.service.OrderService;
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
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AcceptOrderTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    MenuService menuService;

    @Autowired
    CafeteriaService cafeteriaService;

    private static ObjectMapper objectMapper = new ObjectMapper();


    @Before
    public void setUp() throws Exception {
        /*
        =================== 식당추가 =======================
         */
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

        Long cafeteriaId = cafeteriaService.saveCafeteria(cafeteriaPostReqDTO);
        Cafeteria cafeteria = cafeteriaService.findCafeteria(cafeteriaId);
        /*
        ======================= 식당 오픈 =======================
         */
        cafeteriaService.openCafeteria(cafeteriaId);
        /*
        =================== 메뉴추가1 =======================
         */
        MenuPostReqDTO menuPostReqDTO = new MenuPostReqDTO();
        menuPostReqDTO.setIsSeason(false);
        menuPostReqDTO.setCafeteriaId(cafeteriaId);
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
        /*
        =================== 메뉴추가2 =======================
         */
        MenuPostReqDTO menuPostReqDTO2 = new MenuPostReqDTO();
        menuPostReqDTO2.setIsSeason(false);
        menuPostReqDTO2.setCafeteriaId(cafeteriaId);
        menuPostReqDTO2.setName("아메리카노");
        menuPostReqDTO2.setPrice(1000);
        menuPostReqDTO2.setCategory("COFFEE");

        List<MenuPostReqDTO.MenuOptionDetailDTO> menuOptionDetailDTOS3 = new ArrayList<>();
        menuOptionDetailDTOS3.add(new MenuPostReqDTO.MenuOptionDetailDTO("HOT", 500));
        menuOptionDetailDTOS3.add(new MenuPostReqDTO.MenuOptionDetailDTO("ICE", 500));

        List<MenuPostReqDTO.MenuOptionDetailDTO> menuOptionDetailDTOS4 = new ArrayList<>();
        menuOptionDetailDTOS4.add(new MenuPostReqDTO.MenuOptionDetailDTO("SHOT-1", 700));
        menuOptionDetailDTOS4.add(new MenuPostReqDTO.MenuOptionDetailDTO("SHOT-2", 1400));

        List<MenuPostReqDTO.MenuOptionGroupDTO> menuOptionGroupDTO2 = new ArrayList<>();
        menuOptionGroupDTO2.add(new MenuPostReqDTO.MenuOptionGroupDTO("온도", true, menuOptionDetailDTOS3));
        menuOptionGroupDTO2.add(new MenuPostReqDTO.MenuOptionGroupDTO("샷 추가", true, menuOptionDetailDTOS4));


        menuPostReqDTO2.setMenuOptionGroupDTOS(menuOptionGroupDTO2);

        //when
        Long findId2 = menuService.saveMenu(menuPostReqDTO2);
        /*
        =================== 사용자추가 =======================
         */

        /*
        =================== 주문하기 =======================
         */
        //given
        OrderPostReqDTO orderPostReqDTO = new OrderPostReqDTO();
        List<OrderPostReqDTO.CartMenuItem> cartMenuItems = new ArrayList<>();
        orderPostReqDTO.setCustomerId(0L);
        orderPostReqDTO.setCafeteriaId(1L);
        orderPostReqDTO.setRequestMessage("무많이 주시고 일회용젓가락 10개 주세요");
        orderPostReqDTO.setIsTakeOut(true);

        /*================ 메뉴 1 =====================
         */
        OrderPostReqDTO.CartMenuItem menu1 = new OrderPostReqDTO.CartMenuItem();
        menu1.setMenuId(1L);
        menu1.setCount(1);


        List<OrderPostReqDTO.CartOptionGroup> optionGroups1 = new ArrayList<>();

        OrderPostReqDTO.CartOptionGroup optionGroup1 = new OrderPostReqDTO.CartOptionGroup("온도");

        List<OrderPostReqDTO.CartMenuOptionDetail> optionsDetails1 = new ArrayList<>();

        OrderPostReqDTO.CartMenuOptionDetail optionDetail1 = new OrderPostReqDTO.CartMenuOptionDetail("HOT", 500, 1);
        optionsDetails1.add(optionDetail1);
        optionGroup1.setCartOptions(optionsDetails1);

        List<OrderPostReqDTO.CartMenuOptionDetail> optionDetails2 = new ArrayList<>();
        OrderPostReqDTO.CartOptionGroup optionGroup2 = new OrderPostReqDTO.CartOptionGroup("샷");

        OrderPostReqDTO.CartMenuOptionDetail optionDetail2 = new OrderPostReqDTO.CartMenuOptionDetail("샷", 500, 2);
        optionDetails2.add(optionDetail2);
        optionGroup2.setCartOptions(optionDetails2);

        optionGroups1.add(optionGroup1);
        optionGroups1.add(optionGroup2);
        menu1.setCartOptionGroups(optionGroups1);


        /*================ 메뉴 2 =====================
         */
        OrderPostReqDTO.CartMenuItem menu2 = new OrderPostReqDTO.CartMenuItem();
        menu2.setMenuId(2L);
        menu2.setCount(1);

        List<OrderPostReqDTO.CartOptionGroup> optionGroups2 = new ArrayList<>();

        List<OrderPostReqDTO.CartMenuOptionDetail> optionDetails3 = new ArrayList<>();
        OrderPostReqDTO.CartOptionGroup optionGroup3 = new OrderPostReqDTO.CartOptionGroup();
        optionGroup3.setCartOptionGroupName("조리/비조리");

        OrderPostReqDTO.CartMenuOptionDetail optionDetail3 = new OrderPostReqDTO.CartMenuOptionDetail();
        optionDetail3.setCartMenuOptionDetailName("조리");
        optionDetail3.setCartOptionDetailPrice(500);
        optionDetail3.setCartOptionDetailCount(1);

        optionDetails3.add(optionDetail3);
        optionGroup3.setCartOptions(optionDetails3);

        List<OrderPostReqDTO.CartMenuOptionDetail> optionDetails4 = new ArrayList<>();
        OrderPostReqDTO.CartOptionGroup optionGroup4 = new OrderPostReqDTO.CartOptionGroup();
        optionGroup4.setCartOptionGroupName("추가양념");

        OrderPostReqDTO.CartMenuOptionDetail optionDetail4 = new OrderPostReqDTO.CartMenuOptionDetail();
        optionDetail4.setCartMenuOptionDetailName("간장맛");
        optionDetail4.setCartOptionDetailPrice(500);
        optionDetail4.setCartOptionDetailCount(2);

        OrderPostReqDTO.CartMenuOptionDetail optionDetail5 = new OrderPostReqDTO.CartMenuOptionDetail();
        optionDetail5.setCartMenuOptionDetailName("양념맛");
        optionDetail5.setCartOptionDetailPrice(500);
        optionDetail5.setCartOptionDetailCount(2);

        optionDetails4.add(optionDetail4);
        optionDetails4.add(optionDetail5);
        optionGroup4.setCartOptions(optionDetails4);

        optionGroups2.add(optionGroup3);
        optionGroups2.add(optionGroup4);
        menu2.setCartOptionGroups(optionGroups2);

        /* 주문 메뉴 설정 */
        cartMenuItems.add(menu1);
        cartMenuItems.add(menu2);
        orderPostReqDTO.setCartMenuItems(cartMenuItems);
        orderPostReqDTO.setCustomerName("정태승");
        orderService.placeOrder(orderPostReqDTO);
        orderService.payOrder(1L);
    }

    @Test
    public void payOrderTest_default() throws Exception {
        //given
        OrderPatchReqDTO orderPatchReqDTO = new OrderPatchReqDTO(30, 1L);
        String param = objectMapper.writeValueAsString(orderPatchReqDTO);
        //when
        mockMvc.perform(patch("/order/accept")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(param)
                )
                .andExpect(
                        status().isAccepted()
                ).andDo(
                        print()
                );
        Order order = orderRepository.findById(1L).get();

        //then
        Assert.assertEquals(order.getOrderStatus(), OrderStatus.PROCEEDING);
        Assert.assertEquals(order.getEstimatedTime(), orderPatchReqDTO.estimatedTime);
    }
}

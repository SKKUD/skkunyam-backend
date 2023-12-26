package com.skkudteam3.skkusirenorder.src.controller;

import com.skkudteam3.skkusirenorder.common.response.BaseResponse;
import com.skkudteam3.skkusirenorder.common.response.BaseResponseStatus;
import com.skkudteam3.skkusirenorder.src.dto.MenuPostReqDTO;
import com.skkudteam3.skkusirenorder.src.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    /*
    [POST] /menu/
    STAFF
    => menu 등록
     */
    @PostMapping("/")
    public BaseResponse<BaseResponseStatus> registerMenu(@RequestBody MenuPostReqDTO menuPostReqDTO){
        Long menuId = menuService.saveMenu(menuPostReqDTO);
        return new BaseResponse<>(BaseResponseStatus.MENU_REGISTER_SUCCESS);
    }

    /*
    [PATCH] /menu/
    STAFF
    => menu 수정
     */

    /*
    [DELETE] /menu/
    STAFF
    => menu 삭제
     */

    /*
    [GET] /menu/page=?
    STAFF, CUSTOMER
    => 해당 식당 menu들 확인 (페이징추가 필요)
     */

    /*
    [GET] /menu/detail
    STAFF, CUSTOMER
    => 특정 menu 상세 확인 (페이징추가 필요)
     */

    /*
    [GET] /menu/today
    CUSTOMER
    => 오늘의 메뉴 (해당 식당 ID + 메뉴 사진 + 메뉴 이름 + 메뉴 가격)
     */

    /*
     [PATCH] /menu/soldout
     menu 품절 여부 등록
     */


}

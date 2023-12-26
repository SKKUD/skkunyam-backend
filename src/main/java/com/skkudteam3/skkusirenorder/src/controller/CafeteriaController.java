package com.skkudteam3.skkusirenorder.src.controller;

import com.skkudteam3.skkusirenorder.common.Constant;
import com.skkudteam3.skkusirenorder.common.response.BaseResponse;
import com.skkudteam3.skkusirenorder.common.response.BaseResponseStatus;
import com.skkudteam3.skkusirenorder.src.dto.CafeteriaPatchReqDTO;
import com.skkudteam3.skkusirenorder.src.dto.CafeteriaPostReqDTO;
import com.skkudteam3.skkusirenorder.src.service.CafeteriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cafeteria")
public class CafeteriaController {
    private final CafeteriaService cafeteriaService;

    /*
        [POST] /cafeteria/
        STAFF
        => 가게 등록
     */
    @PostMapping("/")
    public BaseResponse<BaseResponseStatus> registerCafeteria(@RequestBody CafeteriaPostReqDTO cafeteriaPostReqDTO){
        Long cafeteriaId = cafeteriaService.saveCafeteria(cafeteriaPostReqDTO);
        return new BaseResponse<>(BaseResponseStatus.CAFETERIA_CREATE);
    }


    /*
        [PATCH] /cafeteria/open/{cafeteriaId}
        STAFF
        => 가게 개장
     */
    @PatchMapping("/open/{cafeteria_id}")
    public BaseResponse<BaseResponseStatus> openCafeteria(@PathVariable Long cafeteria_id){
        Boolean openStatus = cafeteriaService.openCafeteria(cafeteria_id);
        return new BaseResponse<>(BaseResponseStatus.CAFETERIA_OPEN_SUCCESS);
    }

    /*
        [PATCH] /cafeteria/close/{cafeteriaId}
        STAFF
        => 가게 폐장
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/close/{cafeteria_id}")
    public BaseResponse<BaseResponseStatus> closeCafeteria(@PathVariable Long cafeteria_id){
        Boolean openStatus = cafeteriaService.closeCafeteria(cafeteria_id);
        return new BaseResponse<>(BaseResponseStatus.CAFETERIA_CLOSE_SUCCESS);
    }

    /*
        [PATCH] /cafeteria/
        STAFF
        => 가게 정보 수정
     */
    @PatchMapping("/")
    public BaseResponse<BaseResponseStatus> updateCafeteria(@RequestBody CafeteriaPatchReqDTO cafeteriaPatchReqDTO){
        cafeteriaService.updateCafeteria(cafeteriaPatchReqDTO);
        return new BaseResponse<>(BaseResponseStatus.CAFETERIA_UPDATE_SUCCESS);
    }

    /*
        [GET] /cafeteria/
        STAFF
        => 가게 정보 확인 (가게 사진 + 가게 이름 + 가게 위치 + 가게 소개 + 연락처 + 영업요일 + 영업시간)
     */


    /*
        [GET] /cafeteria/all/campus=?&page=?
        CUSTOMER
        => 모든 가게 정보 조회
     */

    /*
        [GET] /cafeteria/detail
        CUSTOMER
        => 선택한 가게 상세 정보 조회 (가게 사진 + 가게 이름 + 가게 위치 + 가게 소개 + 지도)
     */

}

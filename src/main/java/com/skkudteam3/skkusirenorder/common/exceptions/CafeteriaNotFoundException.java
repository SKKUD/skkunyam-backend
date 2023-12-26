package com.skkudteam3.skkusirenorder.common.exceptions;


import com.skkudteam3.skkusirenorder.common.response.BaseResponseStatus;

public class CafeteriaNotFoundException extends BaseException{
    public CafeteriaNotFoundException() {
        super(BaseResponseStatus.CAFETERIA_NOT_FOUND);
    }
}

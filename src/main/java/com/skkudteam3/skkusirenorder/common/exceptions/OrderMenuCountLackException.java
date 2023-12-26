package com.skkudteam3.skkusirenorder.common.exceptions;


import com.skkudteam3.skkusirenorder.common.response.BaseResponseStatus;

public class OrderMenuCountLackException extends BaseException{
    public OrderMenuCountLackException() {
        super(BaseResponseStatus.ORDER_MENU_COUNT_LACK);
    }
}

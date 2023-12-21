package com.skkudteam3.skkusirenorder.common.exceptions;

import com.skkudteam3.skkusirenorder.common.response.BaseResponseStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException{
    private BaseResponseStatus status;

    public BaseException(BaseResponseStatus status) {
        super(status.getMessage());
        this.status = status;
    }
}

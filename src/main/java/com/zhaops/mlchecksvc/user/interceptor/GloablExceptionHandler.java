package com.zhaops.mlchecksvc.user.interceptor;

import com.zhaops.mlchecksvc.user.dto.ResultModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author SoYuan
 */
@ControllerAdvice
public class GloablExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultModel<String> handleException(Exception e) {
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "服务器出错";
        }
        ResultModel<String> jsonObject = new ResultModel<>();
        jsonObject.setMsg(msg);
        jsonObject.setData(e.getMessage());
        jsonObject.setCode(-1);
        return jsonObject;
    }
}

package cn.crabapples.system.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO 通用返回值DTO
 *
 * @author Mr.He
 * 2019/9/21 17:45
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Getter
@Setter
public class ResponseDTO {
    @Getter
    enum Status {
        SUCCESS(200),
        ERROR(500),
        AUTH_FAIL(401);
        int code;

        Status(int code) {
            this.code = code;
        }
    }

    private static final int SUCCESS = 200;
    private static final int ERROR = 500;
    private int status;
    private String message;
    private Object data;
    private long time;

    private ResponseDTO(Status status, String message, Object data) {
        this.status = status.code;
        this.message = message;
        this.data = data;
        this.time = System.currentTimeMillis();
    }

    private ResponseDTO(int code, String message, Object data) {
        this.status = code;
        this.message = message;
        this.data = data;
        this.time = System.currentTimeMillis();
    }

    public static ResponseDTO returnSuccess(String message, Object data) {
        return new ResponseDTO(Status.SUCCESS, message, data);
    }

    public static ResponseDTO returnSuccess(String message) {
        return returnSuccess(message, null);

    }

    public static ResponseDTO returnError(String message, Object data) {
        return new ResponseDTO(Status.ERROR, message, data);
    }

    public static ResponseDTO returnError(String message) {
        return returnError(message, null);
    }

    public static ResponseDTO returnAuthFail(String message, Object data) {
        return new ResponseDTO(Status.AUTH_FAIL, message, data);
    }

    public static ResponseDTO returnAuthFail(String message) {
        return returnAuthFail(message, null);
    }

    public static ResponseDTO returnCustom(int status, String message, Object data) {
        return new ResponseDTO(status, message, data);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

package site.mhjn.demo.response;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(0, "操作成功"),
    FAILURE(9999, "操作失败"),

    VALIDATE_FAILED(1000, "参数校验失败"),

    PARAMETER_ERROR(1001, "参数错误"),

    BUSINESS_ERROR(2000, "业务错误");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

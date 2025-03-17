package site.mhjn.demo.assets;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.validation.FieldError;
import site.mhjn.demo.assets.exception.BusinessException;
import site.mhjn.demo.assets.kit.StringKit;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {
    private int code;
    private String tag;
    private String message;
    private Object data;
    private Set<ValidError> validErrors;

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.tag = resultCode.name();
    }

    public static Result success(Object data) {
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result failure(String message) {
        Result result = new Result(ResultCode.FAILURE);
        if (StringKit.isNotEmpty(message)) {
            result.setMessage(message);
        }
        return result;
    }

    public static Result validateFailed(List<FieldError> fieldErrors) {
        Result result = new Result(ResultCode.VALIDATE_FAILED);
        Set<ValidError> validErrors = new HashSet<>();
        fieldErrors.forEach(fieldError -> validErrors.add(new ValidError(fieldError.getField(), fieldError.getDefaultMessage())));
        result.setValidErrors(validErrors);
        return result;
    }

    public static Result parameterError(String message) {
        Result result = new Result(ResultCode.PARAMETER_ERROR);
        if (StringKit.isNotEmpty(message)) {
            result.setMessage(message);
        }
        return result;
    }

    public static Result businessError(BusinessException e) {
        Result result = new Result(ResultCode.BUSINESS_ERROR);
        if (StringKit.isNotEmpty(e.getMessage())) {
            result.setMessage(e.getMessage());
        }
        return result;
    }
}

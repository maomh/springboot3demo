package site.mhjn.demo.response;

import lombok.Data;

@Data
public class ValidError {
    private String field;
    private String message;

    public ValidError(String field, String message) {
        this.field = field;
        this.message = message;
    }
}

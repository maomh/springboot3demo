package site.mhjn.demo.controller.advice;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import site.mhjn.demo.assets.Result;
import site.mhjn.demo.assets.exception.BusinessException;

import java.util.Set;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("valid error.", e);
        return ResponseEntity.ok(Result.validateFailed(e.getBindingResult().getFieldErrors()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Result> handleBusinessException(BusinessException e) {
        log.info("business error.", e);
        return ResponseEntity.ok(Result.businessError(e));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handleException(Exception e) {
        log.error("internal error.", e);
        return ResponseEntity.ok(Result.failure(e.getMessage()));
    }
}

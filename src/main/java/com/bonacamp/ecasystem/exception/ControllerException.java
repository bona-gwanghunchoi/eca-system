package com.bonacamp.ecasystem.exception;

import com.bonacamp.ecasystem.domain.base.dto.ResponseDto;
import com.bonacamp.ecasystem.util.slack.service.Slack;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;

@RequiredArgsConstructor
@ControllerAdvice
public class ControllerException {
    private final Slack slack;

    /**
     * Valid 오류 처리
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<ResponseDto> validException(Errors errors) {
        errors.getAllErrors().forEach(e -> {
            slack.warn(e.getDefaultMessage());
        } );
        return ResponseEntity
                .badRequest()
                .body(new ResponseDto(-1, errors.getAllErrors().get(0).getDefaultMessage()));
    }

    /**
     * 서비스 도중 에러 처리
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> ExceptionHandler(Exception e) {
        slack.error(e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(-1, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() + " : " + e.getMessage()));
    }

    /**
     * 파라미터 오류 처리
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ResponseDto> handler404() {
        slack.warn("404 Error : Parameter Not Found");
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseDto(-1, HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    /**
     * 405 에러 발생 시 핸들링 처리
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseDto> handler405Error() {
        slack.warn("Method Not Support");
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ResponseDto(-1, HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ResponseDto> handleNoHandlerFoundException(NoHandlerFoundException e, HttpServletRequest request) {
        slack.error(e);
        System.out.println(request.toString());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseDto(-1, HttpStatus.NOT_FOUND.getReasonPhrase()));
    }
}

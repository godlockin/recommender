package com.st.recommender.common;

import com.st.recommender.model.output.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public <T> ResponseEntity<ResponseWrapper<T>> exceptionHandle(HttpServletRequest request, Exception e, HandlerMethod handlerMethod) {
        e.printStackTrace();

        log.error("Error happened on url:[{}]", request.getRequestURI());
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.OK);
        String errMsg = "调用【" + handlerMethod.getMethod().getName() + "]时";
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
            errMsg += "参数校验失败：" + buildErrMsgByBindingError(me.getBindingResult().getAllErrors());
        } else if (e instanceof IllegalArgumentException) {
            IllegalArgumentException ie = (IllegalArgumentException) e;
            errMsg += "参数校验失败：" + String.format("[%s]", ie.getLocalizedMessage());
        } else if (e instanceof BindException) {
            BindException be = (BindException) e;
            errMsg += buildErrMsgByBindingError(be.getAllErrors());
        } else if (e instanceof HttpMessageNotReadableException) {
            errMsg += "参数校验失败：requestBody 不存在";
        } else {
            errMsg += e.getMessage();
        }

        return bodyBuilder.body(ResponseWrapper.failure(0, errMsg));
    }

    private String buildErrMsgByBindingError(List<ObjectError> objectErrors) {
        return objectErrors
                .stream()
                .map(ObjectError::getDefaultMessage)
                .map(error -> String.format("[%s]", error))
                .collect(Collectors.joining(", "));
    }

}

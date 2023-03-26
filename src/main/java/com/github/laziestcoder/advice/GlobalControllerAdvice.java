/**
 * @author Towfiqul Islam
 * @since 3/27/23 12:54 AM
 */

package com.github.laziestcoder.advice;

import com.github.laziestcoder.dto.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    RestResponse globalExceptionHandler(Exception ex) {
        System.out.println(MessageFormat.format("Exception : {0} {1}", ex.getMessage(), ex));
        return RestResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Exception : " + ex.getMessage())
                .data(null)
                .build();
    }
}

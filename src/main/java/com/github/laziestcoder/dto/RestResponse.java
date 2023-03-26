/**
 * @author Towfiqul Islam
 * @since 3/27/23 12:55 AM
 */

package com.github.laziestcoder.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class RestResponse {

    private int statusCode;
    private String message;
    private Object data;

    public RestResponse(int statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public RestResponse() {
    }
}

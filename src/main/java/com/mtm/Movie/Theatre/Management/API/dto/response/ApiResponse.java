package com.mtm.Movie.Theatre.Management.API.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse <T>{

    private boolean success;
    private int code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> ok(String message){
        return ApiResponse.<T>builder()
                .success(true)
                .code(200)
                .message(message)
                .build();
    }

    public static <T> ApiResponse<T> ok(T data){
        return ApiResponse.<T>builder()
                .success(true)
                .code(200)
                .message("Success")
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> ok(String message, T data){
        return ApiResponse.<T>builder()
                .success(true)
                .code(200)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> created(String message){
        return ApiResponse.<T>builder()
                .success(true)
                .code(201)
                .message(message)
                .build();
    }

    public static <T> ApiResponse<T> failed(String message){
        return ApiResponse.<T>builder()
                .success(false)
                .code(400)
                .message(message)
                .build();
    }

    public static <T> ApiResponse<T> notFound(String message){
        return ApiResponse.<T>builder()
                .success(false)
                .code(404)
                .message(message)
                .build();
    }

    public static <T> ApiResponse<T> notFound(String message, T data){
        return ApiResponse.<T>builder()
                .success(false)
                .code(404)
                .message(message)
                .data(data)
                .build();
    }

    public static ApiResponse<?> badRequest(String message) {
        return ApiResponse.builder()
                .success(false)
                .code(400)
                .message(message)
                .build();
    }

}

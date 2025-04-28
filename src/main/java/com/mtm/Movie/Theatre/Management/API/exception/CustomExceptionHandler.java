package com.mtm.Movie.Theatre.Management.API.exception;

import com.mtm.Movie.Theatre.Management.API.dto.response.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    public final ResponseEntity<Object> handleMovieNotFoundException(MovieNotFoundException exception, WebRequest request){
        ErrorResponseDto responseDto = new ErrorResponseDto(exception.getMessage(), String.format("Path is %s", request.getDescription(false)), LocalDateTime.now());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUsernameNotFoundException(UserNotFoundException exception, WebRequest request){
        ErrorResponseDto responseDto = new ErrorResponseDto(exception.getMessage(), String.format("Path is %s", request.getDescription(false)), LocalDateTime.now());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShowtimeNotFoundException.class)
    public final ResponseEntity<Object> handleShowtimeNotFoundException(ShowtimeNotFoundException exception, WebRequest request){
        ErrorResponseDto responseDto = new ErrorResponseDto(exception.getMessage(), String.format("Path is %s", request.getDescription(false)), LocalDateTime.now());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TheatreNotFoundException.class)
    public final ResponseEntity<Object> handleTheatreNotFoundException(TheatreNotFoundException exception, WebRequest request){
        ErrorResponseDto responseDto = new ErrorResponseDto(exception.getMessage(), String.format("Path is %s", request.getDescription(false)), LocalDateTime.now());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TicketBookingNotFoundException.class)
    public final ResponseEntity<Object> handleTicketBookingNotFoundException(TicketBookingNotFoundException exception, WebRequest request){
        ErrorResponseDto responseDto = new ErrorResponseDto(exception.getMessage(), String.format("Path is %s", request.getDescription(false)), LocalDateTime.now());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object> handleBadRequestException(BadRequestException exception, WebRequest request){
        ErrorResponseDto responseDto = new ErrorResponseDto(exception.getMessage(), String.format("Path is %s", request.getDescription(false)), LocalDateTime.now());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
}

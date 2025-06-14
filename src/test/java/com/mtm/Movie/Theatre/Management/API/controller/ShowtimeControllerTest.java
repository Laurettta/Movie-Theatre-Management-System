package com.mtm.Movie.Theatre.Management.API.controller;

import com.mtm.Movie.Theatre.Management.API.dto.request.ShowtimeRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.ShowtimeResponseDto;
import com.mtm.Movie.Theatre.Management.API.service.ShowtimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ShowtimeControllerTest {

    @Mock
    private ShowtimeService showtimeService;

    @InjectMocks
    private ShowtimeController showtimeController;

    private ShowtimeRequestDto showtimeRequestDto;
    private ShowtimeResponseDto showtimeResponseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        showtimeRequestDto = new ShowtimeRequestDto("Movie1", "Theatre1", LocalDateTime.parse("2025-05-05T14:00"));
        showtimeResponseDto = new ShowtimeResponseDto("1", "Movie1", "Theatre1", LocalDateTime.parse("2025-05-05T14:00"));
    }

    @Test
    void saveShowtime_success() {
        when(showtimeService.saveShowtime(showtimeRequestDto)).thenReturn(showtimeResponseDto);

        ShowtimeResponseDto result = showtimeController.saveShowtime(showtimeRequestDto);

        assertEquals("1", result.getId());
        assertEquals("Movie1", result.getMovieId());
        verify(showtimeService, times(1)).saveShowtime(showtimeRequestDto);
    }

    @Test
    void getShowtimeMovie_success() {
        List<ShowtimeResponseDto> showtimes = List.of(showtimeResponseDto);
        when(showtimeService.getShowtimeByMovieId("Movie1")).thenReturn(ResponseEntity.ok(showtimes));

        ResponseEntity<List<ShowtimeResponseDto>> result = showtimeController.getShowtimeMovie("Movie1");

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(1, result.getBody().size());
        assertEquals("Movie1", result.getBody().get(0).getMovieId());
        verify(showtimeService, times(1)).getShowtimeByMovieId("Movie1");
    }

    @Test
    void getShowtimeByTheatre_success() {
        List<ShowtimeResponseDto> showtimes = List.of(showtimeResponseDto);
        when(showtimeService.getShowtimeByTheatre("Theatre1")).thenReturn(ResponseEntity.ok(showtimes));

        ResponseEntity<List<ShowtimeResponseDto>> result = showtimeController.getShowtimeByTheatre("Theatre1");

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(1, result.getBody().size());
        assertEquals("Theatre1", result.getBody().get(0).getTheatreId());
        verify(showtimeService, times(1)).getShowtimeByTheatre("Theatre1");
    }

    @Test
    void deleteShowtime_success() {
        when(showtimeService.deleteShowtimeById("1")).thenReturn(ResponseEntity.noContent().build());

        ResponseEntity<Object> result = showtimeController.deleteShowtime("1");

        assertEquals(204, result.getStatusCodeValue());
        verify(showtimeService, times(1)).deleteShowtimeById("1");
    }

    @Test
    void deleteShowtime_notFound() {
        when(showtimeService.deleteShowtimeById("999")).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<Object> result = showtimeController.deleteShowtime("999");

        assertEquals(404, result.getStatusCodeValue());
        verify(showtimeService, times(1)).deleteShowtimeById("999");
    }
}

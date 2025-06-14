package com.mtm.Movie.Theatre.Management.API.service.impl;

import com.mtm.Movie.Theatre.Management.API.dto.request.ShowtimeRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.ShowtimeResponseDto;
import com.mtm.Movie.Theatre.Management.API.exception.ShowtimeNotFoundException;
import com.mtm.Movie.Theatre.Management.API.mapper.ShowtimeMapper;
import com.mtm.Movie.Theatre.Management.API.model.Showtime;
import com.mtm.Movie.Theatre.Management.API.repository.ShowtimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ShowtimeServiceImplTest {

    @Mock
    private ShowtimeRepository showtimeRepository;

    @Mock
    private ShowtimeMapper showtimeMapper;

    @InjectMocks
    private ShowtimeServiceImpl showtimeService;

    private Showtime showtime;
    private ShowtimeRequestDto requestDto;
    private ShowtimeResponseDto responseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        showtime = new Showtime("1", "movie123", "theatre456", LocalDateTime.parse("2025-05-05T12:00"),100);
        requestDto = new ShowtimeRequestDto("movie123", "theatre456", LocalDateTime.parse("2025-05-05T12:00"));
        responseDto = new ShowtimeResponseDto("1", "movie123", "theatre456",    LocalDateTime.parse("2025-05-05T12:00"));
    }

    @Test
    void saveShowtime_success() {
        when(showtimeMapper.fromDto(requestDto)).thenReturn(showtime);
        when(showtimeRepository.save(showtime)).thenReturn(showtime);
        when(showtimeMapper.fromShowtime(showtime)).thenReturn(responseDto);

        ShowtimeResponseDto result = showtimeService.saveShowtime(requestDto);

        assertEquals("movie123", result.getMovieId());
        assertEquals("theatre456", result.getTheatreId());
        verify(showtimeRepository).save(showtime);
    }

    @Test
    void getShowtimeByMovieId_success() {
        List<Showtime> showtimes = List.of(showtime);
        List<ShowtimeResponseDto> dtoList = List.of(responseDto);

        when(showtimeRepository.findByMovieId("movie123")).thenReturn(showtimes);
        when(showtimeMapper.toDtoList(showtimes)).thenReturn(dtoList);

        ResponseEntity<List<ShowtimeResponseDto>> result = showtimeService.getShowtimeByMovieId("movie123");

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(1, result.getBody().size());
        assertEquals("movie123", result.getBody().get(0).getMovieId());
    }

    @Test
    void getShowtimeByTheatre_success() {

        List<Showtime> showtimes = List.of(showtime);
        List<ShowtimeResponseDto> dtoList = List.of(responseDto);

        when(showtimeRepository.findByTheatreId("theatre456")).thenReturn(showtimes);
        when(showtimeMapper.toDtoList(showtimes)).thenReturn(dtoList);

        ResponseEntity<List<ShowtimeResponseDto>> result = showtimeService.getShowtimeByTheatre("theatre456");

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(1, result.getBody().size());
        assertEquals("theatre456", result.getBody().get(0).getTheatreId());
    }

    @Test
    void deleteShowtimeById_success() {
        when(showtimeRepository.findById("1")).thenReturn(Optional.of(showtime));
        doNothing().when(showtimeRepository).deleteById("1");

        ResponseEntity<Object> result = showtimeService.deleteShowtimeById("1");

        assertEquals(204, result.getStatusCodeValue());
        verify(showtimeRepository).deleteById("1");
    }

    @Test
    void deleteShowtimeById_notFound() {
        when(showtimeRepository.findById("999")).thenReturn(Optional.empty());

        assertThrows(ShowtimeNotFoundException.class, () -> showtimeService.deleteShowtimeById("999"));
        verify(showtimeRepository, never()).deleteById(any());
    }


}

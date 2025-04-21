package com.mtm.Movie.Theatre.Management.API.controller;

import com.mtm.Movie.Theatre.Management.API.dto.TheatreRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.TheatreResponseDto;
import com.mtm.Movie.Theatre.Management.API.service.TheatreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TheatreControllerTest {

    @Mock
    private TheatreService theatreService;

    @InjectMocks
    private TheatreController theatreController;

    private TheatreRequestDto theatreRequestDto;
    private TheatreResponseDto theatreResponseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        theatreRequestDto = new TheatreRequestDto("Theatre1", "Location1", 200);
        theatreResponseDto = new TheatreResponseDto("1", "Theatre1", "Location1", 200);
    }

    @Test
    void saveTheatre_success() {
        when(theatreService.saveTheatre(theatreRequestDto)).thenReturn(theatreResponseDto);

        TheatreResponseDto result = theatreController.saveTheatre(theatreRequestDto);

        assertEquals("1", result.getId());
        assertEquals("Theatre1", result.getName());
        assertEquals(200, result.getSeatingCapacity());
        verify(theatreService, times(1)).saveTheatre(theatreRequestDto);
    }

    @Test
    void getAllTheatres_success() {
        List<TheatreResponseDto> theatres = List.of(theatreResponseDto);
        when(theatreService.getAllTheatres()).thenReturn(theatres);

        List<TheatreResponseDto> result = theatreController.getAllTheatres();

        assertEquals(1, result.size());
        assertEquals("Theatre1", result.get(0).getName());
        verify(theatreService, times(1)).getAllTheatres();
    }

    @Test
    void getTheatreById_success() {
        when(theatreService.getTheatreById("1")).thenReturn(ResponseEntity.ok(theatreResponseDto));

        ResponseEntity<TheatreResponseDto> result = theatreController.getTheatreById("1");

        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Theatre1", result.getBody().getName());
        verify(theatreService, times(1)).getTheatreById("1");
    }

    @Test
    void getTheatreById_notFound() {
        when(theatreService.getTheatreById("999")).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<TheatreResponseDto> result = theatreController.getTheatreById("999");

        assertEquals(404, result.getStatusCodeValue());
        verify(theatreService, times(1)).getTheatreById("999");
    }

    @Test
    void updateTheatre_success() {
        when(theatreService.updateTheatre("1", theatreRequestDto)).thenReturn(ResponseEntity.ok(theatreResponseDto));

        ResponseEntity<TheatreResponseDto> result = theatreController.updateTheatre("1", theatreRequestDto);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Theatre1", result.getBody().getName());
        verify(theatreService, times(1)).updateTheatre("1", theatreRequestDto);
    }

    @Test
    void deleteTheatre_success() {
        when(theatreService.deleteTheatre("1")).thenReturn(ResponseEntity.noContent().build());

        ResponseEntity<Object> result = theatreController.deleteTheatre("1");

        assertEquals(204, result.getStatusCodeValue());
        verify(theatreService, times(1)).deleteTheatre("1");
    }

    @Test
    void deleteTheatre_notFound() {
        when(theatreService.deleteTheatre("999")).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<Object> result = theatreController.deleteTheatre("999");

        assertEquals(404, result.getStatusCodeValue());
        verify(theatreService, times(1)).deleteTheatre("999");
    }
}

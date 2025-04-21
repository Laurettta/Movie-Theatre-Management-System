package com.mtm.Movie.Theatre.Management.API.service.impl;

import com.mtm.Movie.Theatre.Management.API.dto.TheatreRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.TheatreResponseDto;
import com.mtm.Movie.Theatre.Management.API.exception.TheatreNotFoundException;
import com.mtm.Movie.Theatre.Management.API.mapper.TheatreMapper;
import com.mtm.Movie.Theatre.Management.API.model.Theatre;
import com.mtm.Movie.Theatre.Management.API.repository.TheatreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TheatreServiceImplTest {

    @InjectMocks
    private TheatreServiceImpl theatreService;

    @Mock
    private TheatreRepository theatreRepository;

    @Mock
    private TheatreMapper theatreMapper;

    private Theatre theatre;
    private TheatreRequestDto requestDto;
    private TheatreResponseDto responseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        theatre = new Theatre("1", "Epic Theatre", "Lagos", 150);
        requestDto = new TheatreRequestDto("Epic Theatre", "Lagos", 150);
        responseDto = new TheatreResponseDto("1", "Epic Theatre", "Lagos", 150);
    }

    @Test
    void saveTheatre_shouldReturnSavedTheatre() {
        when(theatreMapper.fromDto(requestDto)).thenReturn(theatre);
        when(theatreRepository.save(theatre)).thenReturn(theatre);
        when(theatreMapper.fromTheatre(theatre)).thenReturn(responseDto);

        TheatreResponseDto result = theatreService.saveTheatre(requestDto);

        assertEquals(responseDto, result);
        verify(theatreRepository).save(theatre);
    }

    @Test
    void getAllTheatres_shouldReturnListOfTheatreResponseDto() {
        List<Theatre> theatres = List.of(theatre);
        List<TheatreResponseDto> dtoList = List.of(responseDto);

        when(theatreRepository.findAll()).thenReturn(theatres);
        when(theatreMapper.toDtoList(theatres)).thenReturn(dtoList);

        List<TheatreResponseDto> result = theatreService.getAllTheatres();

        assertEquals(dtoList, result);
        verify(theatreRepository).findAll();
    }

    @Test
    void getTheatreById_whenExists_shouldReturnResponseEntity() {
        when(theatreRepository.findById("1")).thenReturn(Optional.of(theatre));
        when(theatreMapper.fromTheatre(theatre)).thenReturn(responseDto);

        ResponseEntity<TheatreResponseDto> result = theatreService.getTheatreById("1");

        assertEquals(ResponseEntity.ok(responseDto), result);
    }

    @Test
    void getTheatreById_whenNotExists_shouldThrowException() {
        when(theatreRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(TheatreNotFoundException.class, () -> theatreService.getTheatreById("1"));
    }

    @Test
    void updateTheatre_whenExists_shouldUpdateAndReturnResponseEntity() {
        Theatre updatedTheatre = new Theatre("1", "New Theatre", "Abuja", 300);
        TheatreRequestDto updateDto = new TheatreRequestDto("New Theatre", "Abuja", 300);
        TheatreResponseDto updatedResponse = new TheatreResponseDto("1", "New Theatre", "Abuja", 300);

        when(theatreRepository.findById("1")).thenReturn(Optional.of(theatre));
        when(theatreRepository.save(any(Theatre.class))).thenReturn(updatedTheatre);
        when(theatreMapper.fromTheatre(updatedTheatre)).thenReturn(updatedResponse);

        ResponseEntity<TheatreResponseDto> result = theatreService.updateTheatre("1", updateDto);

        assertEquals(ResponseEntity.ok(updatedResponse), result);
        verify(theatreRepository).save(theatre);
    }

    @Test
    void updateTheatre_whenNotExists_shouldThrowException() {
        when(theatreRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(TheatreNotFoundException.class, () -> theatreService.updateTheatre("1", requestDto));
    }

    @Test
    void deleteTheatre_whenExists_shouldReturnNoContent() {
        when(theatreRepository.findById("1")).thenReturn(Optional.of(theatre));

        ResponseEntity<Object> result = theatreService.deleteTheatre("1");

        assertEquals(ResponseEntity.noContent().build(), result);
        verify(theatreRepository).deleteById("1");
    }

    @Test
    void deleteTheatre_whenNotExists_shouldThrowException() {
        when(theatreRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(TheatreNotFoundException.class, () -> theatreService.deleteTheatre("1"));
    }
}

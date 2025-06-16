package com.mtm.Movie.Theatre.Management.API.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingKafkaMessageDto {

    private String bookingId;
    private String userId;
    private String movieId;
    private String showtimeId;
    private int seats;
    private LocalDateTime bookingDate;
}

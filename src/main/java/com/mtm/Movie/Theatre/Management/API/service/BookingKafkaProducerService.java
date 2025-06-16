package com.mtm.Movie.Theatre.Management.API.service;

import com.mtm.Movie.Theatre.Management.API.dto.kafka.BookingKafkaMessageDto;

public interface BookingKafkaProducerService {
    void sendBookingMessage(BookingKafkaMessageDto messageDto);
}

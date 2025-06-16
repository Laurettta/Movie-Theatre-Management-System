package com.mtm.Movie.Theatre.Management.API.controller;

import com.mtm.Movie.Theatre.Management.API.dto.kafka.BookingKafkaMessageDto;
import com.mtm.Movie.Theatre.Management.API.service.BookingKafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
@RequiredArgsConstructor
public class KafkaMessageController {

    private final BookingKafkaProducerService bookingKafkaProducerService;

    @PostMapping("/send")
    public ResponseEntity<String> sendBookingToKafka(@RequestBody BookingKafkaMessageDto messageDto) {
        bookingKafkaProducerService.sendBookingMessage(messageDto);
        return ResponseEntity.ok(" Booking message sent to Kafka");
    }
}

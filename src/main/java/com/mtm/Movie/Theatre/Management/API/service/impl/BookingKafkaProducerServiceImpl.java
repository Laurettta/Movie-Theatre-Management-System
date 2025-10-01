package com.mtm.Movie.Theatre.Management.API.service.impl;

import com.mtm.Movie.Theatre.Management.API.dto.kafka.BookingKafkaMessageDto;
import com.mtm.Movie.Theatre.Management.API.service.BookingKafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingKafkaProducerServiceImpl implements BookingKafkaProducerService {

    private final KafkaTemplate<String, BookingKafkaMessageDto> kafkaTemplate;

    @Value("${kafka.topic.booking}")
    private String topic;

    @Override
    public void sendBookingMessage(BookingKafkaMessageDto messageDto)                     {
        kafkaTemplate.send(topic, messageDto);
    }
}

package com.mtm.Movie.Theatre.Management.API.service.impl;

import com.mtm.Movie.Theatre.Management.API.dto.kafka.BookingKafkaMessageDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookingKafkaConsumer {

    @KafkaListener(
            topics = "${kafka.topic.booking}",
            groupId = "movie-group",
            containerFactory = "bookingKafkaListenerContainerFactory"
    )

    public void listen(BookingKafkaMessageDto message) {
        System.out.println(" Received BookingKafkaMessageDto: " + message);
    }

}

package com.mtm.Movie.Theatre.Management.API.service.impl;

import com.mtm.Movie.Theatre.Management.API.service.DummyApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@RequiredArgsConstructor
public class DummyApiServiceImpl implements DummyApiService {

    private final WebClient webClient;

    @Value("${external.api.url}")
    private String apiUrl;

    @Override
    public String getUserById(int id) {
        return webClient.get()
                .uri(apiUrl + "/users/{id}", id)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}

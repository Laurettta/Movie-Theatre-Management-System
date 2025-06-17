package com.mtm.Movie.Theatre.Management.API.controller;

import com.mtm.Movie.Theatre.Management.API.service.DummyApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/external")
public class DummyApiController {

    private final DummyApiService dummyApiService;

    @GetMapping("/user/{id}")
    public ResponseEntity<String> getDummyUser(@PathVariable int id) {
        return ResponseEntity.ok(dummyApiService.getUserById(id));
    }
}

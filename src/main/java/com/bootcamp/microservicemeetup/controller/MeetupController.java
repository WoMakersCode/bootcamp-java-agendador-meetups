package com.bootcamp.microservicemeetup.controller;

import com.bootcamp.microservicemeetup.model.MeetupDTO;
import com.bootcamp.microservicemeetup.model.entity.Meetup;
import com.bootcamp.microservicemeetup.model.entity.Registration;
import com.bootcamp.microservicemeetup.service.MeetupService;
import com.bootcamp.microservicemeetup.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/meetups")
@RequiredArgsConstructor
public class MeetupController {

    private final MeetupService meetupService;
    private final RegistrationService registrationService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Integer create(@RequestBody MeetupDTO meetupDTO) {

        Registration registration = registrationService.getRegistrationByRegistrationAttribute(meetupDTO.getRegistration())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        Meetup entity = Meetup.builder()
                .registration(registration)
                .event(meetupDTO.getEvent())
                .meetupDate("10/10/2021")
                .build();

        entity = meetupService.save(entity);
        return entity.getId();
    }
}

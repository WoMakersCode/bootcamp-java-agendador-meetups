package com.bootcamp.microservicemeetup.service;

import com.bootcamp.microservicemeetup.model.entity.Meetup;

public interface MeetupService {

    Meetup save(Meetup meetup);
}

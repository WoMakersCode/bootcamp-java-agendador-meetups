package com.bootcamp.microservicemeetup.service.impl;

import com.bootcamp.microservicemeetup.exception.BusinessException;
import com.bootcamp.microservicemeetup.model.entity.Meetup;
import com.bootcamp.microservicemeetup.repository.MeetupRepository;
import com.bootcamp.microservicemeetup.service.MeetupService;

public class MeetupServiceImpl implements MeetupService {


    private MeetupRepository repository;

    public MeetupServiceImpl(MeetupRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meetup save(Meetup meetup) {
        if (repository.existsByRegistrationAndNotRegistrated(meetup.getRegistration())) {
            throw new BusinessException("Registration already registered");
        }
        return repository.save(meetup);
    }
}

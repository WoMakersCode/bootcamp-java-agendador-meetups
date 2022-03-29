package com.bootcamp.microservicemeetup.service;

import com.bootcamp.microservicemeetup.model.entity.Registration;

import java.util.Optional;

public interface RegistrationService {

    Registration save(Registration any);

    Optional<Registration> getRegistrationById(Integer id);
}

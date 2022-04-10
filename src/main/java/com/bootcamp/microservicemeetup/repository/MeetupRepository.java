package com.bootcamp.microservicemeetup.repository;

import com.bootcamp.microservicemeetup.model.entity.Meetup;
import com.bootcamp.microservicemeetup.model.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetupRepository extends JpaRepository<Meetup, Integer> {

    boolean existsByRegistrationAndNotRegistrated(Registration registration);
}

package com.bootcamp.microservicemeetup.repository;

import com.bootcamp.microservicemeetup.model.entity.Meetup;
import com.bootcamp.microservicemeetup.model.entity.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MeetupRepository extends JpaRepository<Meetup, Integer> {

    @Query( value = " select l from Meetup as l join l.registration as b where b.id = :registrationId or l.event =:event ")
    Page<Meetup> findByRegistrationOnMeetup(
            @Param("registrationId") Integer registrationId,
            @Param("event") String event,
            Pageable pageable
    );

    Page<Meetup> findByRegistration(Registration registration, Pageable pageable );
}

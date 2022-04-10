package com.bootcamp.microservicemeetup.service;

import com.bootcamp.microservicemeetup.exception.BusinessException;
import com.bootcamp.microservicemeetup.model.entity.Meetup;
import com.bootcamp.microservicemeetup.model.entity.Registration;
import com.bootcamp.microservicemeetup.repository.MeetupRepository;
import com.bootcamp.microservicemeetup.service.impl.MeetupServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class MeetupServiceTest {

    MeetupService meetupService;

    @MockBean
    MeetupRepository meetupRepository;


    @BeforeEach
    public void setUp() {
        this.meetupService = new MeetupServiceImpl(meetupRepository);
    }


    @Test
    @DisplayName("Should save an meetup")
    public void saveMeetupTest() {

        Registration registration = Registration.builder().id(11).build();

        Meetup savingMeetup = Meetup.builder()
                .registration(registration)
                .event("Womakerscode Dados")
                .meetupDate("10/10/2021")
                .build();


        Meetup savedMeetup = Meetup.builder()
                .id(11)
                .meetupDate("10/10/2021")
                .event("Womakerscode Dados")
                .registration(registration)
                .build();

        Mockito.when(meetupRepository.existsByRegistrationAndNotRegistrated(registration)).thenReturn(false);
        Mockito.when(meetupRepository.save(savingMeetup)).thenReturn(savedMeetup);

        Meetup meetup = meetupService.save(savingMeetup);

        assertThat(meetup.getId()).isEqualTo(savedMeetup.getId());
        assertThat(meetup.getEvent()).isEqualTo(savedMeetup.getEvent());
        assertThat(meetup.getRegistration()).isEqualTo(savedMeetup.getRegistration());
        assertThat(meetup.getMeetupDate()).isEqualTo(savedMeetup.getMeetupDate());
    }


    @Test
    @DisplayName("Should throw business exception when save an enrollment already registered")
    public void enrollmentStudentSaveTest() {

        Registration registration = Registration.builder().id(11).build();

        Meetup savingMeetup = Meetup.builder()
                .registration(registration)
                .event("Womakerscode Dados")
                .meetupDate("10/10/2021")
                .build();

        Mockito.when(meetupRepository.existsByRegistrationAndNotRegistrated(registration)).thenReturn(true);

        Throwable exception =  catchThrowable(() -> meetupService.save(savingMeetup));

        assertThat(exception).isInstanceOf(BusinessException.class).hasMessage("Registration already registered");

        Mockito.verify(meetupRepository, Mockito.never()).save(savingMeetup);

    }
}

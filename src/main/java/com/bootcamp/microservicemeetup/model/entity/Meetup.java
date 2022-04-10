package com.bootcamp.microservicemeetup.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meetup {

    private Integer id;
    private String event;
    private Registration registration;
    private String meetupDate;
    private Boolean registered;
}

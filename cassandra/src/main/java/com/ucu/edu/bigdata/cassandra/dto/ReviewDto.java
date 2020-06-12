package com.ucu.edu.bigdata.cassandra.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewDto {

    String id;
    Integer rating;
    Integer helpfulVotes;
    Integer totalVotes;
    Boolean vine;
    Boolean isVerified;
    String headline;
    LocalDate date;
    String body;

}
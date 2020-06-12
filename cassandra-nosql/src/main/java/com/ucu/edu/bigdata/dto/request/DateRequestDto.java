package com.ucu.edu.bigdata.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DateRequestDto {

    LocalDate beginDate;
    LocalDate endDate;
    Integer limit;
}

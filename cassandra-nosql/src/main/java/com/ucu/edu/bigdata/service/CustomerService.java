package com.ucu.edu.bigdata.service;

import com.ucu.edu.bigdata.dto.CustomerDto;
import com.ucu.edu.bigdata.dto.request.DateRequestDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getProductiveCustomers(DateRequestDto dateRequestDto);

    List<CustomerDto> getHaters(DateRequestDto dateRequestDto);

    List<CustomerDto> getBackers(DateRequestDto dateRequestDto);
}

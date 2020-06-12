package com.ucu.edu.bigdata.cassandra.service;

import com.ucu.edu.bigdata.cassandra.dto.CustomerDto;
import com.ucu.edu.bigdata.cassandra.dto.request.DateRequestDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getProductiveCustomers(DateRequestDto dateRequestDto);

    List<CustomerDto> getHaters(DateRequestDto dateRequestDto);

    List<CustomerDto> getBackers(DateRequestDto dateRequestDto);
}

package com.ucu.edu.bigdata.cassandra.service.impl;

import com.ucu.edu.bigdata.cassandra.dto.CustomerDto;
import com.ucu.edu.bigdata.cassandra.dto.request.DateRequestDto;
import com.ucu.edu.bigdata.cassandra.mapper.CustomerMapper;
import com.ucu.edu.bigdata.cassandra.predicate.CustomerPredicate;
import com.ucu.edu.bigdata.cassandra.repository.CustomerRepository;
import com.ucu.edu.bigdata.cassandra.service.CustomerService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerServiceImpl implements CustomerService {

    final CustomerMapper customerMapper;
    final CustomerPredicate customerPredicate;

    @Override
    public List<CustomerDto> getProductiveCustomers(DateRequestDto dateRequestDto) {
        return customerMapper.toDtoList(customerPredicate.getProductiveCustomers(dateRequestDto.getBeginDate(),
                dateRequestDto.getEndDate(), dateRequestDto.getLimit()));
    }

    @Override
    public List<CustomerDto> getHaters(DateRequestDto dateRequestDto) {
        return customerMapper.toDtoList(customerPredicate.getHaters(dateRequestDto.getBeginDate(),
                dateRequestDto.getEndDate(), dateRequestDto.getLimit()));
    }

    @Override
    public List<CustomerDto> getBackers(DateRequestDto dateRequestDto) {
        return customerMapper.toDtoList(customerPredicate.getBackers(dateRequestDto.getBeginDate(),
                dateRequestDto.getEndDate(), dateRequestDto.getLimit()));
    }
}

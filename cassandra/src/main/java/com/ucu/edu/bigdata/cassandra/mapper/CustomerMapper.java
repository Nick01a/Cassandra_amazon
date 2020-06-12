package com.ucu.edu.bigdata.cassandra.mapper;

import com.ucu.edu.bigdata.cassandra.dto.CustomerDto;
import com.ucu.edu.bigdata.cassandra.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);

    Customer toEntity(CustomerDto customerDto);

    List<CustomerDto> toDtoList(List<Customer> customerList);

    List<Customer> toEntityList(List<CustomerDto> customerDtoList);
}

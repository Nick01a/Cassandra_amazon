package com.ucu.edu.bigdata.service.impl;

import com.ucu.edu.bigdata.dto.CustomerDto;
import com.ucu.edu.bigdata.dto.request.DateRequestDto;
import com.ucu.edu.bigdata.service.CustomerService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerServiceImpl implements CustomerService {

    final CqlTemplate cqlTemplate;

    @Override
    public List<CustomerDto> getProductiveCustomers(DateRequestDto dateRequestDto) {
        String cql = "SELECT customer_id, COUNT(review_id) as total_reviews " +
                "FROM customer_reviews " +
                "WHERE review_date >= ? AND review_date <= ? " +
                "AND verified_purchase = true " +
                "GROUP BY customer_id " +
                "ALLOW FILTERING";

        List<CustomerDto> haters = cqlTemplate.query(cql, (row, rowNum) -> CustomerDto.builder()
                .id(row.getInt("customer_id"))
                .totalReviews(row.getLong("total_reviews"))
                .build(), dateRequestDto.getBeginDate(), dateRequestDto.getEndDate());

        return haters.parallelStream()
                .sorted(Comparator.comparing(CustomerDto::getTotalReviews).reversed())
                .limit(dateRequestDto.getLimit())
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> getHaters(DateRequestDto dateRequestDto) {
        String cql = "SELECT customer_id, COUNT(review_id) as total_reviews " +
                "FROM customer_reviews " +
                "WHERE review_date >= ? AND review_date <= ? " +
                "AND star_rating <= 2 " +
                "GROUP BY customer_id " +
                "ALLOW FILTERING";

        List<CustomerDto> haters = cqlTemplate.query(cql, (row, rowNum) -> CustomerDto.builder()
                .id(row.getInt("customer_id"))
                .totalReviews(row.getLong("total_reviews"))
                .build(), dateRequestDto.getBeginDate(), dateRequestDto.getEndDate());

        return haters.parallelStream()
                .sorted(Comparator.comparing(CustomerDto::getTotalReviews).reversed())
                .limit(dateRequestDto.getLimit())
                .collect(Collectors.toList());

    }

    @Override
    public List<CustomerDto> getBackers(DateRequestDto dateRequestDto) {
        String cql = "SELECT customer_id, COUNT(review_id) as total_reviews " +
                "FROM customer_reviews " +
                "WHERE review_date >= ? AND review_date <= ? " +
                "AND star_rating >= 4 " +
                "GROUP BY customer_id " +
                "ALLOW FILTERING";

        List<CustomerDto> backers = cqlTemplate.query(cql, (row, rowNum) -> CustomerDto.builder()
                .id(row.getInt("customer_id"))
                .totalReviews(row.getLong("total_reviews"))
                .build(), dateRequestDto.getBeginDate(), dateRequestDto.getEndDate());

        return backers.parallelStream()
                .sorted(Comparator.comparing(CustomerDto::getTotalReviews).reversed())
                .limit(dateRequestDto.getLimit())
                .collect(Collectors.toList());
    }
}

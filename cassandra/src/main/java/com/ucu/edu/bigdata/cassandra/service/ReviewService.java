package com.ucu.edu.bigdata.cassandra.service;

import com.ucu.edu.bigdata.cassandra.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> findByProductIdAndRating(String productId, Integer rating);

    List<ReviewDto> findByCustomerId(Long customerId);

}

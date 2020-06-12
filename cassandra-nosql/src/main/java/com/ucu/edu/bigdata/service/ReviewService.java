package com.ucu.edu.bigdata.service;

import com.ucu.edu.bigdata.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> findByProductIdAndRating(String productId, Integer rating);

    List<ReviewDto> findByCustomerId(Integer customerId);

}

package com.ucu.edu.bigdata.cassandra.service.impl;

import com.ucu.edu.bigdata.cassandra.dto.ReviewDto;
import com.ucu.edu.bigdata.cassandra.mapper.ReviewMapper;
import com.ucu.edu.bigdata.cassandra.repository.ReviewRepository;
import com.ucu.edu.bigdata.cassandra.service.ReviewService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewServiceImpl implements ReviewService {

    final ReviewRepository reviewRepository;
    final ReviewMapper reviewMapper;

    @Override
    public List<ReviewDto> findByProductIdAndRating(String productId, Integer rating) {
        return reviewMapper.toDtoList(reviewRepository.findAllByProductIdAndRating(productId, rating));
    }

    @Override
    public List<ReviewDto> findByCustomerId(Long customerId) {
        return reviewMapper.toDtoList(reviewRepository.findAllByCustomerId(customerId));
    }
}

package com.ucu.edu.bigdata.service.impl;

import com.ucu.edu.bigdata.dto.ReviewDto;
import com.ucu.edu.bigdata.service.ReviewService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewServiceImpl implements ReviewService {

    final CqlTemplate cqlTemplate;

    @Override
    public List<ReviewDto> findByProductIdAndRating(String productId, Integer rating) {
        String cql = "SELECT * FROM product_reviews WHERE product_id = ? AND star_rating = ?";
        return cqlTemplate.query(cql, (row, rowNum) ->
                        ReviewDto.builder()
                                .id(row.getString("review_id"))
                                .body(row.getString("review_body"))
                                .date(row.getLocalDate("review_date"))
                                .headline(row.getString("review_headline"))
                                .rating(row.getInt("star_rating"))
                                .build()
                , productId, rating);

    }

    @Override
    public List<ReviewDto> findByCustomerId(Integer customerId) {
        String cql = "SELECT * FROM customer_reviews WHERE customer_id = ?";
        return cqlTemplate.query(cql, (row, rowNum) ->
                        ReviewDto.builder()
                                .id(row.getString("review_id"))
                                .body(row.getString("review_body"))
                                .date(row.getLocalDate("review_date"))
                                .headline(row.getString("review_headline"))
                                .rating(row.getInt("star_rating"))
                                .build()
                , customerId);
    }
}

package com.ucu.edu.bigdata.cassandra.repository;

import com.ucu.edu.bigdata.cassandra.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    List<Review> findAllByProductIdAndRating(String productId, Integer rating);

    List<Review> findAllByCustomerId(Long customerId);

}

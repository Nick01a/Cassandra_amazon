package com.ucu.edu.bigdata.controller;

import com.ucu.edu.bigdata.dto.ReviewDto;
import com.ucu.edu.bigdata.service.ReviewService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/reviews")
@CrossOrigin("*")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewController {

    private static final String LOG_MESSAGE_WITH_REVIEW_ID = "Endpoint - {}({}) call";

    final ReviewService reviewService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<List<ReviewDto>> findByProductAndRating(@PathVariable String productId,
                                                                  @RequestParam Integer rating) {
        log.debug(LOG_MESSAGE_WITH_REVIEW_ID, "getProductReviews", productId);
        return new ResponseEntity<>(reviewService.findByProductIdAndRating(productId, rating), HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<List<ReviewDto>> findByCustomer(@PathVariable Integer customerId) {
        log.debug(LOG_MESSAGE_WITH_REVIEW_ID, "getCustomerReviews", customerId);
        return new ResponseEntity<>(reviewService.findByCustomerId(customerId), HttpStatus.OK);
    }
}

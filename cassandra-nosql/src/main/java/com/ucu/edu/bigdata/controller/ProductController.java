package com.ucu.edu.bigdata.controller;

import com.ucu.edu.bigdata.dto.ProductDto;
import com.ucu.edu.bigdata.dto.ReviewDto;
import com.ucu.edu.bigdata.dto.request.DateRequestDto;
import com.ucu.edu.bigdata.service.ProductService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/products")
@CrossOrigin("*")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductController {

    private static final String LOG_MESSAGE = "Endpoint - {}() call";
    private static final String LOG_MESSAGE_WITH_PRODUCT_ID = "Endpoint - {}({}) call";

    final ProductService productService;

    @GetMapping("/{productId}/reviews")
    public ResponseEntity<List<ReviewDto>> getProductReviews(@PathVariable String productId) {
        log.debug(LOG_MESSAGE_WITH_PRODUCT_ID, "getProductReviews", productId);
        return new ResponseEntity<>(productService.getProductReviews(productId), HttpStatus.OK);
    }

    @GetMapping("/best")
    public ResponseEntity<List<ProductDto>> getBestProducts(@RequestParam Integer limit) {
        log.debug(LOG_MESSAGE, "getBestProducts");
        return new ResponseEntity<>(productService.getBestProducts(limit), HttpStatus.OK);
    }

    @PostMapping("/favorite")
    public ResponseEntity<List<ProductDto>> getFavoriteProducts(@RequestBody DateRequestDto dateRequestDto) {
        log.debug(LOG_MESSAGE, "getFavoriteProducts");
        return new ResponseEntity<>(productService.getFavoriteProducts(dateRequestDto), HttpStatus.OK);
    }
}

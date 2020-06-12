package com.ucu.edu.bigdata.cassandra.service;

import com.ucu.edu.bigdata.cassandra.dto.ProductDto;
import com.ucu.edu.bigdata.cassandra.dto.ReviewDto;
import com.ucu.edu.bigdata.cassandra.dto.request.DateRequestDto;

import java.util.List;

public interface ProductService {

    List<ReviewDto> getProductReviews(String productId);

    List<ProductDto> getFavoriteProducts(DateRequestDto dateRequestDto);

    List<ProductDto> getBestProducts(Integer limit);
}

package com.ucu.edu.bigdata.service;

import com.ucu.edu.bigdata.dto.ProductDto;
import com.ucu.edu.bigdata.dto.ReviewDto;
import com.ucu.edu.bigdata.dto.request.DateRequestDto;

import java.util.List;

public interface ProductService {

    List<ReviewDto> getProductReviews(String productId);

    List<ProductDto> getFavoriteProducts(DateRequestDto dateRequestDto);

    List<ProductDto> getBestProducts(Integer limit);
}

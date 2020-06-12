package com.ucu.edu.bigdata.cassandra.service.impl;

import com.ucu.edu.bigdata.cassandra.dto.ProductDto;
import com.ucu.edu.bigdata.cassandra.dto.ReviewDto;
import com.ucu.edu.bigdata.cassandra.dto.request.DateRequestDto;
import com.ucu.edu.bigdata.cassandra.mapper.ProductMapper;
import com.ucu.edu.bigdata.cassandra.mapper.ReviewMapper;
import com.ucu.edu.bigdata.cassandra.predicate.ProductPredicate;
import com.ucu.edu.bigdata.cassandra.repository.ProductRepository;
import com.ucu.edu.bigdata.cassandra.service.ProductService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;
    final ProductPredicate productPredicate;
    final ReviewMapper reviewMapper;
    final ProductMapper productMapper;

    @Override
    public List<ReviewDto> getProductReviews(String productId) {
        return reviewMapper.toDtoList(productRepository.getOne(productId).getReviews());
    }

    @Override
    public List<ProductDto> getFavoriteProducts(DateRequestDto dateRequestDto) {
        return productMapper.toDtoList(productPredicate.getFavoriteProducts(dateRequestDto.getBeginDate(), dateRequestDto.getEndDate(),
                dateRequestDto.getLimit()));
    }

    @Override
    public List<ProductDto> getBestProducts(Integer limit) {
        return productMapper.toDtoList(productPredicate.getBestProducts(limit));
    }
}

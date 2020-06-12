package com.ucu.edu.bigdata.service.impl;


import com.ucu.edu.bigdata.dto.CustomerDto;
import com.ucu.edu.bigdata.dto.ProductDto;
import com.ucu.edu.bigdata.dto.ReviewDto;
import com.ucu.edu.bigdata.dto.request.DateRequestDto;
import com.ucu.edu.bigdata.service.ProductService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {

    final CqlTemplate cqlTemplate;

    @Override
    public List<ReviewDto> getProductReviews(String productId) {
        String cql = "SELECT * FROM product_reviews WHERE product_id = ?";
        return cqlTemplate.query(cql, (row, rowNum) ->
                        ReviewDto.builder()
                                .id(row.getString("review_id"))
                                .body(row.getString("review_body"))
                                .date(row.getLocalDate("review_date"))
                                .headline(row.getString("review_headline"))
                                .rating(row.getInt("star_rating"))
                                .build()
                , productId);
    }

    @Override
    public List<ProductDto> getFavoriteProducts(DateRequestDto dateRequestDto) {
        String cql = "SELECT product_id, product_title, product_parent, product_category, COUNT(review_id) as total_reviews " +
                "FROM reviews_by_date_star " +
                "WHERE review_date >= ? AND review_date <= ? " +
                "GROUP BY product_id " +
                "ALLOW FILTERING";

        List<ProductDto> productDtos = cqlTemplate.query(cql, (row, rowNum) -> ProductDto.builder()
                .id(row.getString("product_id"))
                .title(row.getString("product_title"))
                .parent(row.getString("product_parent"))
                .category(row.getString("product_category"))
                .totalReviews(row.getLong("total_reviews"))
                .build(), dateRequestDto.getBeginDate(), dateRequestDto.getEndDate());

        return productDtos.parallelStream()
                .sorted(Comparator.comparing(ProductDto::getTotalReviews).reversed())
                .limit(dateRequestDto.getLimit())
                .collect(Collectors.toList());

    }

    @Override
    public List<ProductDto> getBestProducts(Integer limit) {
        String cql = "SELECT product_id, product_title, product_parent, product_category, COUNT(verified_purchase) as total_verified " +
                "FROM best_products " +
                "WHERE verified_purchase = true " +
                "GROUP BY product_id, verified_purchase " +
                "ALLOW FILTERING";

        List<ProductDto> productDtos = cqlTemplate.query(cql, (row, rowNum) -> ProductDto.builder()
                .id(row.getString("product_id"))
                .title(row.getString("product_title"))
                .parent(row.getString("product_parent"))
                .category(row.getString("product_category"))
                .totalReviews(row.getLong("total_verified"))
                .build());

        return productDtos.parallelStream()
                .sorted(Comparator.comparing(ProductDto::getTotalReviews).reversed())
                .limit(limit)
                .collect(Collectors.toList());

    }
}

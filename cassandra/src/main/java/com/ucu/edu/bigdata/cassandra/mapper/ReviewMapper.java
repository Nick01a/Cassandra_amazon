package com.ucu.edu.bigdata.cassandra.mapper;

import com.ucu.edu.bigdata.cassandra.dto.ReviewDto;
import com.ucu.edu.bigdata.cassandra.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ReviewMapper {

    ReviewDto toDto(Review review);

    Review toEntity(ReviewDto reviewDto);

    List<ReviewDto> toDtoList(List<Review> reviewList);

    List<Review> toEntityList(List<ReviewDto> reviewDtoList);
}

package com.ucu.edu.bigdata.cassandra.mapper;

import com.ucu.edu.bigdata.cassandra.dto.ProductDto;
import com.ucu.edu.bigdata.cassandra.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    List<ProductDto> toDtoList(List<Product> productList);

    List<Product> toEntityList(List<ProductDto> productDtoList);
}

package com.restaurant.court_service.infrastructure.output.jpa.mapper;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.infrastucture.output.jpa.entity.BrandEntity;
import com.emazon.stock.utils.Constants;
import org.mapstruct.Mapper;

@Mapper(componentModel = Constants.MAPPER_STRING)
public interface BrandEntityMapper {
    BrandEntity toEntity(Brand brand);

    Brand toBrand(BrandEntity brandEntity);
}

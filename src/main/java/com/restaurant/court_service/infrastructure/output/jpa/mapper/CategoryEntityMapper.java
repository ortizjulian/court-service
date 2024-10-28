package com.restaurant.court_service.infrastructure.output.jpa.mapper;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.infrastucture.output.jpa.entity.CategoryEntity;
import com.emazon.stock.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = Constants.MAPPER_STRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryEntityMapper {

    CategoryEntity toEntity(Category category);

    Category toCategory(CategoryEntity categoryEntity);
}


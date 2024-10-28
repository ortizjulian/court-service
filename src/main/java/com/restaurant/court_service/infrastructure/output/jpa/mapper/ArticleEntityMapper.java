package com.restaurant.court_service.infrastructure.output.jpa.mapper;

import com.emazon.stock.domain.model.Article;
import com.emazon.stock.infrastucture.output.jpa.entity.ArticleEntity;
import com.emazon.stock.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = Constants.MAPPER_STRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ArticleEntityMapper {

    @Mapping(source = Constants.BRAND_ENTITY, target = Constants.BRAND)
    @Mapping(source = Constants.ARTICLE_CATEGORIES, target = Constants.CATEGORIES)
    Article toArticle(ArticleEntity articleEntity);

    @Mapping(source = Constants.BRAND, target = Constants.BRAND_ENTITY)
    @Mapping(source = Constants.CATEGORIES, target = Constants.ARTICLE_CATEGORIES)
    ArticleEntity toEntity(Article article);

    List<Article> toArticleList(List<ArticleEntity> articleEntities);
}

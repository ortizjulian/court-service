package com.restaurant.court_service.infrastructure.output.jpa.mapper;

import com.emazon.stock.domain.model.Article;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;
import com.emazon.stock.infrastucture.output.jpa.entity.ArticleEntity;
import com.emazon.stock.infrastucture.output.jpa.entity.BrandEntity;
import com.emazon.stock.infrastucture.output.jpa.entity.CategoryEntity;
import com.emazon.stock.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = Constants.MAPPER_STRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {CategoryEntityMapper.class, BrandEntityMapper.class, ArticleEntityMapper.class })
public interface PageMapper {

    CategoryEntityMapper CATEGORY_ENTITY_MAPPER = Mappers.getMapper(CategoryEntityMapper.class);
    BrandEntityMapper BRAND_ENTITY_MAPPER = Mappers.getMapper(BrandEntityMapper.class);
    ArticleEntityMapper ARTICLE_ENTITY_MAPPER = Mappers.getMapper(ArticleEntityMapper.class);

    default PageCustom<Category> toCategoryPageCustom(Page<CategoryEntity> page) {
        PageCustom<Category> pageCustom = new PageCustom<>();
        List<Category> categories = page.getContent().stream()
                .map(CATEGORY_ENTITY_MAPPER::toCategory)
                .toList();

        pageCustom.setContent(categories);
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setHasNext(page.hasNext());
        pageCustom.setHasPrevious(page.hasPrevious());
        return pageCustom;
    }

    default PageCustom<Brand> toBrandPageCustom(Page<BrandEntity> page) {
        PageCustom<Brand> pageCustom = new PageCustom<>();
        List<Brand> brands = page.getContent().stream()
                .map(BRAND_ENTITY_MAPPER::toBrand)
                .toList();

        pageCustom.setContent(brands);
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setHasNext(page.hasNext());
        pageCustom.setHasPrevious(page.hasPrevious());
        return pageCustom;
    }

    default PageCustom<Article> toArticlePageCustom(Page<ArticleEntity> page) {
        PageCustom<Article> pageCustom = new PageCustom<>();
        List<Article> articles = page.getContent().stream()
                .map(ARTICLE_ENTITY_MAPPER::toArticle)
                .toList();

        pageCustom.setContent(articles);
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setHasNext(page.hasNext());
        pageCustom.setHasPrevious(page.hasPrevious());
        return pageCustom;
    }
}

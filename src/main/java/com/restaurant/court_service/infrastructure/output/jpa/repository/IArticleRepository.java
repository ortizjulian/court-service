package com.restaurant.court_service.infrastructure.output.jpa.repository;

import com.emazon.stock.infrastucture.output.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long>, JpaSpecificationExecutor<ArticleEntity> {
}

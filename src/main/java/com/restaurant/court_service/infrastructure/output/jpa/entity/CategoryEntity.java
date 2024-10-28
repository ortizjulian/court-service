package com.restaurant.court_service.infrastructure.output.jpa.entity;

import com.emazon.stock.utils.Constants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = Constants.MAX_CHARACTERS_NAME_CATEGORY)
    private String name;

    @Column(length = Constants.MAX_CHARACTERS_DESCRIPTION_CATEGORY)
    private String description;

    @ManyToMany(mappedBy = "articleCategories")
    Set<ArticleEntity> likes;
}

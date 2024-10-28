package com.restaurant.court_service.infrastructure.output.jpa.adapter;

import com.restaurant.court_service.domain.spi.ICategoryPersistencePort;
import com.restaurant.court_service.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;

    @Override
    public Boolean existById(Long id) {
        return categoryRepository.existsById(id);
    }
}

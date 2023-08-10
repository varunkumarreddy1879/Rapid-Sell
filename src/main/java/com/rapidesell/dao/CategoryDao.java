package com.rapidesell.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rapidesell.model.Category;

import java.util.Optional;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {
    @Override
    Optional<Category> findById(Integer integer);

    Category save(Category category);
}

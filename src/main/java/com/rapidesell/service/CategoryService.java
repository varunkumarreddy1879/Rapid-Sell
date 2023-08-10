package com.rapidesell.service;

import com.rapidesell.dao.CategoryDao;
import com.rapidesell.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;

    public void save(Category category) {
        categoryDao.save(category);
    }


    public void deleteCategory(int categoryId) {
        Optional<Category> optionalCategory=categoryDao.findById(categoryId);
        if(optionalCategory.isPresent()){
            categoryDao.delete(optionalCategory.get());
        }
    }
}

package com.rapidesell.service;

import com.rapidesell.dao.FoodDao;
import com.rapidesell.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    @Autowired
    FoodDao foodDao;

    public List<Food> findByCategoryId(int categoryId) {
        return foodDao.findByCategoryId(categoryId);
    }

    public List<Food> findAll() {
        return foodDao.findAll();
    }


    public void deleteFoodsOfCategory(int categoryId) {
        foodDao.deleteAllByCategoryId(categoryId);
    }

    public Food getFood(int foodId) {
        Optional<Food> optionalFood=foodDao.findById(foodId);
        if(optionalFood.isPresent()){
            return optionalFood.get();
        }
        return null;
    }

    public void deleteFood(int foodId) {
        Optional<Food> optionalFood=foodDao.findById(foodId);
        if(optionalFood.isPresent()){
            foodDao.delete(optionalFood.get());
        }
        return;
    }

    public List<Food> searchProductByName(String foodName) {
        return foodDao.findAllByNameContainingIgnoreCase(foodName);
    }

    public Food updateProduct(int id, String name, String description, Double price, Double discount, int categoryId, Part part, String fileName) {
        Food food=new Food();
        food.setCategoryId(categoryId);
        food.setImagePath(fileName);
        food.setName(name);
        food.setPrice(price);
        food.setDiscount(discount);
        food.setDescription(description);
        food.setId(id);
        return foodDao.save(food);
    }

    public Food addProduct(int categoryId, String fileName, String name, Double price, String description, Double discount) {
        Food food=new Food();
        food.setCategoryId(categoryId);
        food.setImagePath(fileName);
        food.setName(name);
        food.setPrice(price);
        food.setDiscount(discount);
        food.setDescription(description);
        return foodDao.save(food);
    }
}

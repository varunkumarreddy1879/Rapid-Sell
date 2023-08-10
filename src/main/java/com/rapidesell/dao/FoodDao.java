package com.rapidesell.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.rapidesell.model.Food;

import javax.crypto.spec.OAEPParameterSpec;

@Repository
public interface FoodDao extends JpaRepository<Food, Integer> {
	
	List<Food> findAllByNameContainingIgnoreCase(String name);
	List<Food> findByCategoryId(int categpryId);
	
	@Query(value="delete from Food f where f.categoryId = :categoryId")
	void deleteProductByCategoryId(@Param("categoryId") int categoryId);

    void deleteAllByCategoryId(int categoryId);

	Food save(Food food);
}

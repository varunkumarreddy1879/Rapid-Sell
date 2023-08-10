package com.rapidesell.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rapidesell.model.User;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	Optional<User> findById(Integer integer);

	Optional<User> findByEmailidAndPassword(String emailId, String password);
	Optional<User> findByEmailidAndMobileno(String emailId, String mobileNo);

}
